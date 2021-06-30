package com.sea.library.common.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : ViewBinding> ComponentActivity.binding() = lazy {
    inflateViewBinding<T>(layoutInflater).apply {
        setContentView(root)
        if (this is ViewDataBinding) lifecycleOwner = this@binding
    }
}

inline fun <reified T : ViewBinding> Fragment.binding() = FragmentViewBindingDelegate<T> { requireView().binding() }

inline fun <reified T : ViewBinding> Fragment.binding(method: Method) = FragmentViewBindingDelegate<T> {
    if (method == Method.BIND) requireView().binding() else inflateViewBinding(layoutInflater)
}

inline fun <reified T : ViewBinding> ViewGroup.binding(attachToParent: Boolean = true) = lazy {
    inflateViewBinding<T>(LayoutInflater.from(context), if (attachToParent) this else null, attachToParent)
}

inline fun <reified T : ViewBinding> View.binding() = T::class.java.getMethod("bind", View::class.java).invoke(null, this) as T

inline fun <reified T : ViewBinding> inflateViewBinding(layoutInflater: LayoutInflater) = T::class.java.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as T

inline fun <reified T : ViewBinding> inflateViewBinding(parent: ViewGroup) = inflateViewBinding<T>(LayoutInflater.from(parent.context), parent, false)

inline fun <reified T : ViewBinding> inflateViewBinding(layoutInflater: LayoutInflater, parent: ViewGroup ?, attachToParent: Boolean) = T::class.java.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java).invoke(null, layoutInflater, parent, attachToParent) as T

inline fun Fragment.doOnDestroyView(crossinline block: () -> Unit) = viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroyView() {
            block.invoke()
        }

    }
)

enum class Method { BIND, INFLATE }

interface ViewBindingLifecycleOwner {

    fun onDestroyViewBinding(destroyingBinding: ViewBinding)

}

class FragmentViewBindingDelegate<T : ViewBinding>(private val block: () -> T) : ReadOnlyProperty<Fragment, T> {

    private var binding: T ? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        if (binding == null) {
            binding = block().apply {
                if (this is ViewDataBinding) lifecycleOwner = thisRef.viewLifecycleOwner
            }
            thisRef.doOnDestroyView {
                val viewBinding = binding
                if (thisRef is ViewBindingLifecycleOwner && viewBinding !=null) {
                    thisRef.onDestroyViewBinding(viewBinding)
                }
                binding = null
            }
        }
        return binding !!
    }

}