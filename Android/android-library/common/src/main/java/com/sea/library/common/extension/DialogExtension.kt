package com.sea.library.common.extension

import android.app.Dialog
import androidx.viewbinding.ViewBinding

inline fun <reified VB : ViewBinding> Dialog.binding() = lazy {
    inflateBinding<VB>(layoutInflater).also { setContentView(it.root) }
}