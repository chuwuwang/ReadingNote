package com.nsz.kotlin.spannable

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import kotlinx.android.synthetic.main.activity_spannable_string.*
import org.jetbrains.anko.toast

class SpannableStringActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spannable_string)
        initView()
    }

    private fun initView() {
        val spannableStringBuilder = SpannableStringBuilder().also {
            it.append("hello world. click here.")
            val styleSpan = StyleSpan(Typeface.BOLD)
            val relativeSizeSpan = RelativeSizeSpan(1.5f)
            val underlineSpan = UnderlineSpan()
            val backgroundColorSpan = BackgroundColorSpan(Color.RED)
            val foregroundColorSpan = ForegroundColorSpan(Color.BLUE)
            it.setSpan(styleSpan, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            it.setSpan(underlineSpan, 3, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            it.setSpan(relativeSizeSpan, 5, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            it.setSpan(foregroundColorSpan, 8, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            it.setSpan(backgroundColorSpan, 10, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            it.setSpan(clickableSpan, 12, 24, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        tv_message.text = spannableStringBuilder
        // 注意: 必须要设置下面的代码, 否则没有点击效果
        tv_message.movementMethod = LinkMovementMethod.getInstance()
    }

    private val clickableSpan = object : ClickableSpan() {

        override fun onClick(v: View) {
            toast("hi android")
        }

        override fun updateDrawState(textPaint: TextPaint) {
            super.updateDrawState(textPaint)
            // link的颜色
            textPaint.linkColor = Color.BLUE
            // 下划线的宽度
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                textPaint.underlineThickness = 1f
            }
            // 下划线有效
            textPaint.isUnderlineText = true
        }

    }

}