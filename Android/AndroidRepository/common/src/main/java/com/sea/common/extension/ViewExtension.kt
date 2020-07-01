package com.sea.common.extension

import android.view.View
import androidx.annotation.StringRes

fun View.getString(@StringRes resId: Int) = context.getString(resId)