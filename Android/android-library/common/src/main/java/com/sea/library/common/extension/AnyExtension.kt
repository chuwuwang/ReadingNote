package com.sea.library.common.extension

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <T> anyExecute(receiver: T ? , block: T.() -> Unit) {
    if (receiver == null) {
        Log.e("ktx", "The depend on call is null")
    } else {
        receiver.block()
    }
}

inline fun <reified VB : ViewBinding> inflateBinding(layoutInflater: LayoutInflater) = VB::class.java.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as VB

inline fun <reified VB : ViewBinding> inflateBinding(parent: ViewGroup) = inflateBinding<VB>(LayoutInflater.from(parent.context), parent, false)

inline fun <reified VB : ViewBinding> inflateBinding(layoutInflater: LayoutInflater, parent: ViewGroup ?, attachToParent: Boolean) = VB::class.java.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java).invoke(null, layoutInflater, parent, attachToParent) as VB