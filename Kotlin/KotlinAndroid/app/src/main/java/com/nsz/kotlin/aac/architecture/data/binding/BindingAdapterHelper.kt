package com.nsz.kotlin.aac.architecture.data.binding

import android.util.Log
import androidx.databinding.BindingAdapter

object BindingAdapterHelper {

    @BindingAdapter("hello")
    @JvmStatic
    fun setHello(view: MyTextView, name: String) {
        Log.d("lz", "name: $name")
        view.text = name
    }

}