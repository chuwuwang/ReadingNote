package com.sea.library.common.extension

import android.util.Log

inline fun <T> anyExecute(receiver: T ? , block: T.() -> Unit) {
    if (receiver == null) {
        Log.e("ktx", "depend on call is null")
    } else {
        receiver.block()
    }
}