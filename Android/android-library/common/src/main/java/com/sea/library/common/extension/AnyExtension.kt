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