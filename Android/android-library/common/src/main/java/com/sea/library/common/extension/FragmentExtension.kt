package com.sea.library.common.extension

import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified VB : ViewBinding> Fragment.binding() = FragmentBindingDelegate<VB> { requireView().bind() }

interface BindingLifecycleOwner {
    fun onDestroyViewBinding(binding: ViewBinding)
}

inline fun Fragment.doOnDestroyView(crossinline block: () -> Unit) =
    viewLifecycleOwner.lifecycle.addObserver(
        object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroyView() {
                block.invoke()
            }
        }
    )

class FragmentBindingDelegate<VB : ViewBinding>(private val block: () -> VB) : ReadOnlyProperty<Fragment, VB> {
    private var binding: VB ? = null
    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        if (binding == null) {
            binding = block().also {
                if (it is ViewDataBinding) {
                    it.lifecycleOwner = thisRef.viewLifecycleOwner
                }
            }
            thisRef.doOnDestroyView {
                if (thisRef is BindingLifecycleOwner) {
                    val _binding = binding
                    if (_binding != null) {
                        thisRef.onDestroyViewBinding(_binding)
                    } else {
                        Log.e("ktx", "Fragment binding is null")
                    }
                }
                binding = null
            }
        }
        return binding !!
    }
}