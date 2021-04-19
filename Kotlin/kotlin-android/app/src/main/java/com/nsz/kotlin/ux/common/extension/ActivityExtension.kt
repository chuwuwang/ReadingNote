package com.nsz.kotlin.ux.common.extension

import android.app.Activity
import android.view.View

fun <V : View> Activity.bindView(id: Int): Lazy<V> = lazy {
    viewFinder(id) as V
}

val Activity.viewFinder: Activity.(Int) -> View
    get() = { findViewById(it) }

// val textView by bindView<TextView>(R.id.text_view)
// textView.text="执行到我时。才会进行控件初始化"