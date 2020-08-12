package com.sea.common.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.sea.common.R
import kotlinx.android.synthetic.main.common_view_title.view.*

class TitleView : RelativeLayout {

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet ? = null) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet ? = null, defStyle: Int) : super(context, attrs, defStyle) {
        initView()
    }

    private val view = LayoutInflater.from(context).inflate(R.layout.common_view_title, this, true)

    val left_image by lazy { view.left_image }

    val center_text by lazy { view.center_text }

    val right_text by lazy { view.right_text }
    val right_image by lazy { view.right_image }

    private fun initView() {
        right_text.visibility = View.GONE
        right_image.visibility = View.GONE
    }

}