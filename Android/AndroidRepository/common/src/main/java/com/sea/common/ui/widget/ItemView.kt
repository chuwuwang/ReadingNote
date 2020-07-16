package com.sea.common.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.sea.common.R
import kotlinx.android.synthetic.main.common_view_item.view.*

class ItemView : RelativeLayout {

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet ? = null) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet ? = null, defStyle: Int) : super(context, attrs, defStyle) {
        initView()
    }

    private val view = LayoutInflater.from(context).inflate(R.layout.common_view_item, this, true)

    private fun initView() {
        view.left_image.visibility = View.GONE
        view.separate_match.visibility = View.GONE
    }

    fun getLeftTextView(): TextView {
        return view.left_text
    }

    fun getLeftImageView(): ImageView {
        return view.left_image
    }

    fun getRightTextView(): TextView {
        return view.right_text
    }

    fun getRightImageView(): ImageView {
        return view.right_image
    }

    fun getWrapSeparateView(): View {
        return view.separate_wrap
    }

    fun getMatchSeparateView(): View {
        return view.separate_match
    }

}