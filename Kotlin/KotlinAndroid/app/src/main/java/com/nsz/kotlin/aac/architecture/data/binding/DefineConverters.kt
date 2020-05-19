package com.nsz.kotlin.aac.architecture.data.binding

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.databinding.BindingConversion

object DefineConverters {

    @BindingConversion
    @JvmStatic
    fun convertColorToDrawable(color: String): ColorDrawable {
        val parseColor = Color.parseColor(color)
        return ColorDrawable(parseColor)
    }

}