package com.sea.library.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.sea.library.common.R

class EditTextVerificationCode @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet ? = null,
    defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    companion object {
        private const val TAG = "VerificationCode"
        const val BLINK = 500L
        const val DEFAULT_CODE_SIZE = 6
        const val DEFAULT_CODE_MARGIN = 16f
    }

    private var itemWidth = 128
    private var itemHeight = 128

    private var size = DEFAULT_CODE_SIZE
    private var margin = DEFAULT_CODE_MARGIN

    private var itemBackground: Drawable ? = null

    private var itemCursorFlag = true
    private var itemCursorVisible = true
    private var itemCursorDrawable: Drawable ? = null

    private var blink: Blink ? = null

    private val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextVerificationCode, defStyleAttr, 0)

    init {
        try {
            size = typedArray.getInteger(R.styleable.EditTextVerificationCode_code_size, DEFAULT_CODE_SIZE)
            margin = typedArray.getDimension(R.styleable.EditTextVerificationCode_code_margin, DEFAULT_CODE_MARGIN)
            itemBackground = typedArray.getDrawable(R.styleable.EditTextVerificationCode_code_background)
            itemCursorVisible = typedArray.getBoolean(R.styleable.EditTextVerificationCode_code_cursor_visible, true)
            itemCursorDrawable = typedArray.getDrawable(R.styleable.EditTextVerificationCode_code_cursor_drawable)
            if (itemCursorDrawable == null) {
                itemCursorDrawable = GradientDrawable().apply {
                    val color = ContextCompat.getColor(context, android.R.color.darker_gray)
                    setColor(color)
                    setSize(3, 0)
                }
            }
        } finally {
            typedArray.recycle()
        }
        isEnabled = false
        isFocusable = true
        isLongClickable = false
        isCursorVisible = false
        setMaxLength(size)
        setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onTextContextMenuItem(id: Int): Boolean {
        return false
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measuredWidth: Int
        val measuredHeight: Int
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        if (widthMode == MeasureSpec.AT_MOST) {
            val newWidth = (itemWidth * size + (size - 1) * margin).toInt()
            measuredWidth = MeasureSpec.makeMeasureSpec(newWidth, MeasureSpec.EXACTLY)
        } else {
            val totalWidth = widthSize - margin * (size - 1)
            itemWidth = (totalWidth / size).toInt()
            measuredWidth = MeasureSpec.makeMeasureSpec(widthSize, widthMode)
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            measuredHeight = MeasureSpec.makeMeasureSpec(itemHeight, MeasureSpec.EXACTLY)
        } else {
            itemHeight = heightSize
            measuredHeight = MeasureSpec.makeMeasureSpec(heightSize, heightMode)
        }
        setMeasuredDimension(measuredWidth, measuredHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas != null) {
            drawBackground(canvas)
            drawText(canvas)
            drawCursor(canvas)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.e(TAG, "onAttachedToWindow")
        resumeBlink()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        suspendBlink()
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        Log.e(TAG, "onAttachedToWindow: $hasWindowFocus")
        if (hasWindowFocus) {
            resumeBlink()
        } else {
            suspendBlink()
        }
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        Log.e(TAG, "onFocusChanged: $focused")
        if (focused) {
            resumeBlink()
        }
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (text != null) {
            if (text.length >= size) {
                suspendBlink()
            } else if (text.length + 1 == size && lengthBefore == 1) {
                resumeBlink()
            }
        }
    }

    private fun drawBackground(canvas: Canvas) {
        val background = itemBackground
        if (background != null) {
            val currentIndex = 0.coerceAtLeast(editableText.length)
            val count = canvas.save()
            for (i in 0 until size) {
                background.bounds = Rect(0, 0, itemWidth, itemHeight)
                if (currentIndex == i) {
                    background.state = intArrayOf(android.R.attr.state_selected)
                } else {
                    background.state = intArrayOf(android.R.attr.state_enabled)
                }
                background.draw(canvas)
                canvas.translate(itemWidth + margin, 0f)
            }
            canvas.restoreToCount(count)
        }
    }

    private fun drawText(canvas: Canvas) {
        val count = canvas.save()
        canvas.translate(0f, 0f)
        val textColor = currentTextColor
        for (i in editableText.indices) {
            val textWidth = paint.measureText(editableText[i].toString())
            val fontMetrics = Paint.FontMetrics()
            paint.getFontMetrics(fontMetrics)
            paint.color = textColor
            val x = (itemWidth + margin) * i + itemWidth / 2f - textWidth / 2f
            val y = itemHeight / 2f - (fontMetrics.top + fontMetrics.bottom) / 2f
            canvas.drawText(editableText[i].toString(), x, y, paint)
        }
        canvas.restoreToCount(count)
    }

    private fun drawCursor(canvas: Canvas) {
        if (itemCursorFlag) {
            itemCursorFlag = false
        } else {
            itemCursorFlag = true
            return
        }
        val drawable = itemCursorDrawable
        if (itemCursorVisible && drawable != null) {
            val currentIndex = 0.coerceAtLeast(editableText.length)
            val count = canvas.save()
            val line = layout.getLineForOffset(selectionStart)
            val top = layout.getLineTop(line)
            val bottom = layout.getLineBottom(line)
            val rect = Rect()
            drawable.getPadding(rect)
            drawable.bounds = Rect(0, top - rect.top, drawable.intrinsicWidth, bottom + rect.bottom)
            val height = drawable.bounds.height()
            val dx = (itemWidth + margin) * currentIndex + itemWidth / 2f - drawable.intrinsicWidth / 2f
            val dy = (itemHeight - height) / 2f
            canvas.translate(dx, dy)
            drawable.draw(canvas)
            canvas.restoreToCount(count)
        }
    }

    private fun setMaxLength(length: Int) {
        if (length >= 0) {
            val filter = LengthFilter(length)
            filters = arrayOf<InputFilter>(filter)
        }
    }

    private fun suspendBlink() {
        val action = blink
        if (action != null) {
            action.cancel()
        } else {
            //
        }
    }

    private fun resumeBlink() {
        if (blink == null) {
            blink = Blink()
        }
        val action = blink
        if (action != null) {
            action.flicker()
            val shouldBlink = shouldBlink()
            if (shouldBlink) {
                postDelayed(blink, BLINK)
            }
        }
    }

    private fun shouldBlink(): Boolean {
        if (itemCursorVisible) {
            val start = selectionStart
            if (start < 0) return false
            val end = selectionEnd
            return if (end < 0) false else start == end
        }
        return false
    }

    inner class Blink : Runnable {

        private var cancelled = false

        override fun run() {
            if (cancelled) {
                return
            }
            removeCallbacks(this)
            val shouldBlink = shouldBlink()
            if (shouldBlink) {
                if (layout != null) {
                    invalidate()
                }
                postDelayed(this, BLINK)
            }
        }

        fun cancel() {
            cancelled = true
            removeCallbacks(this)
        }

        fun flicker() {
            cancelled = false
            removeCallbacks(blink)
        }

    }

}