package com.sea.library.common.widget.animation

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import androidx.annotation.FloatRange
import androidx.core.content.ContextCompat
import com.sea.library.common.R
import kotlin.math.min
import kotlin.properties.Delegates.observable

class ProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet ? = null,
    defStyleAttr: Int = 0
) : InfiniteAnimateView(context, attrs, defStyleAttr) {

    private val defaultStrokeColor: Int = ContextCompat.getColor(context, R.color.bright_foreground_dark_disabled)
    private val defaultProgressColor: Int = ContextCompat.getColor(context, R.color.white)
    private val defaultBackgroundColor: Int = Color.parseColor("#20000000")

    private val progressPadding = context.resources.getDimension(R.dimen.default_margin_padding_4)

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = defaultBackgroundColor
    }
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = defaultStrokeColor
        strokeWidth = context.resources.getDimension(R.dimen.default_margin_padding_2)
    }
    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = defaultProgressColor
        strokeWidth = context.resources.getDimension(R.dimen.default_margin_padding_4)
    }

    @FloatRange(from = .0, to = 1.0, toInclusive = false)
    var progress: Float = 0f
        set(value) {
            field = when {
                value < 0f -> 0f
                value > 1f -> 1f
                else -> value
            }
            sweepAngle = convertToSweepAngle(field)
            invalidate()
        }

    // [0, 360)
    private var currentAngle: Float by observable(0f) { _, _, _ -> invalidate() }
    private var sweepAngle: Float by observable(MIN_SWEEP_ANGLE) { _, _, _ -> invalidate() }

    private var backgroundRadius: Float = 0f
    private val progressRect: RectF = RectF()

    private val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView, defStyleAttr, 0)

    init {
        try {
            progressPaint.color = typedArray.getColor(R.styleable.ProgressView_progress_color, defaultProgressColor)
            strokePaint.color = typedArray.getColor(R.styleable.ProgressView_progress_stroke_color, defaultStrokeColor)
            backgroundPaint.color = typedArray.getColor(R.styleable.ProgressView_progress_background_color, defaultBackgroundColor)
        } finally {
            typedArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val verticalHalf = (measuredHeight - paddingTop - paddingBottom) / 2f
        val horizontalHalf = (measuredWidth - paddingStart - paddingEnd) / 2f

        val progressOffset = progressPadding + progressPaint.strokeWidth / 2f

        // 由于笔画在线的中心，我们需要为它留出一半的安全空间，否则它将被截断的界限
        backgroundRadius = min(horizontalHalf, verticalHalf) - strokePaint.strokeWidth / 2f

        val progressRectMinSize = 2 * (min(horizontalHalf, verticalHalf) - progressOffset)

        progressRect.left = (measuredWidth - progressRectMinSize) / 2f
        progressRect.top = (measuredHeight - progressRectMinSize) / 2f
        progressRect.right = (measuredWidth + progressRectMinSize) / 2f
        progressRect.bottom = (measuredHeight + progressRectMinSize) / 2f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas != null) {
            // (radius - strokeWidth) - because we don't want to overlap colors (since they by default translucent)
            canvas.drawCircle(progressRect.centerX(), progressRect.centerY(), backgroundRadius - strokePaint.strokeWidth / 2f, backgroundPaint)
            canvas.drawCircle(progressRect.centerX(), progressRect.centerY(), backgroundRadius, strokePaint)
            canvas.drawArc(progressRect, currentAngle, sweepAngle, false, progressPaint)
        }
    }

    override fun createAnimation(): Animator {
        val animator = ValueAnimator.ofFloat(currentAngle, currentAngle + MAX_ANGLE)
        animator.interpolator = LinearInterpolator()
        animator.duration = SPIN_DURATION_MS
        animator.repeatCount = ValueAnimator.INFINITE
        animator.addUpdateListener {
            currentAngle = normalize(it.animatedValue as Float)
        }
        return animator
    }

    /**
     * 将任意角转换至 [0, 360)
     * 比如说 angle = 400.54 => return 40.54
     * angle = 360 => return 0
     */
    private fun normalize(angle: Float): Float {
        val decimal = angle - angle.toInt()
        return (angle.toInt() % MAX_ANGLE) + decimal
    }

    private fun convertToSweepAngle(progress: Float): Float {
        return MIN_SWEEP_ANGLE + progress * (MAX_ANGLE - MIN_SWEEP_ANGLE)
    }

    private companion object {
        private const val MAX_ANGLE = 360
        private const val MIN_SWEEP_ANGLE = 10f
        private const val SPIN_DURATION_MS = 2_000L
    }

}

