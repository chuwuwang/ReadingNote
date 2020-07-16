package com.sea.common.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.StringRes
import com.sea.common.R
import kotlinx.android.synthetic.main.common_view_title.view.*

class TitleView : RelativeLayout {

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet ? = null) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet ? = null, defStyle: Int) : super(context, attrs, defStyle) {
        initView()
    }

    private val view = LayoutInflater.from(context).inflate(R.layout.common_view_title, this, true)

    private fun initView() {
        view.right_text.visibility = View.GONE
        view.right_image.visibility = View.GONE
    }

    fun setTitleText(@StringRes resId: Int) {
        view.center_text.setText(resId)
    }

    fun setTitleText(text: CharSequence) {
        view.center_text.text = text
    }

    fun getLeftImageView(): ImageView = view.left_image

    fun getRightImageView(): ImageView = view.right_image

    fun getRightTextView(): TextView = view.right_text

}