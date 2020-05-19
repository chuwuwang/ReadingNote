package com.nsz.kotlin.aac.architecture.data.binding

import android.util.Log
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

object InverseBindingAdapterHelper {

    @BindingAdapter("android:weak", requireAll = false)
    @JvmStatic
    fun setX(view: RatingBar, value: Float) {
        if (view.rating == value) return
        view.rating = value
    }

    @InverseBindingAdapter(attribute = "android:weak", event = "android:weakAttrChanged")
    @JvmStatic
    fun getX(view: RatingBar): Float {
        return view.rating
    }

    @BindingAdapter(
        value = ["android:weakAttrChanged"], requireAll = false
    )
    @JvmStatic
    fun setListener(
        view: RatingBar,
        inverseBindingListener: InverseBindingListener
    ) {
        Log.d("nz", "inverseBindingListener")
        view.setOnRatingBarChangeListener { _, fl, b ->
            Log.d("nz", "fl: $fl b: $b")
            inverseBindingListener.onChange()

        }
    }

}