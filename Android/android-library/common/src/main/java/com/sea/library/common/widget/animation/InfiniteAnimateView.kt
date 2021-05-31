package com.sea.library.common.widget.animation

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.sea.library.common.extension.anyExecute

abstract class InfiniteAnimateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet ? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var animation: Animator ? = null

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        val deepVisible = isDeepVisible()
        if (deepVisible) {
            startAnimation()
        } else {
            stopAnimation()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startAnimation()
    }

    override fun onDetachedFromWindow() {
        stopAnimation()
        super.onDetachedFromWindow()
    }

    protected abstract fun createAnimation(): Animator

    private fun startAnimation() {
        val bool = isAttachedToWindow && isDeepVisible()
        if (bool) {
            if (animation == null) {
                animation = createAnimation().apply { start() }
            }
        }
    }

    private fun stopAnimation() {
        anyExecute(animation) {
            cancel()
        }
        animation = null
    }

    private fun isDeepVisible(): Boolean {
        var isVisible = isVisible
        var parent = parentView
        while (parent != null && isVisible) {
            isVisible = isVisible && parent.isVisible
            parent = parent.parentView
        }
        return isVisible
    }

    private val View.parentView: ViewGroup ? get() = parent as? ViewGroup

}