package com.sea.library.common.extension

import android.view.View
import androidx.viewbinding.ViewBinding

inline fun <reified VB : ViewBinding> View.bind() = VB::class.java.getMethod("bind", View::class.java).invoke(null, this) as VB