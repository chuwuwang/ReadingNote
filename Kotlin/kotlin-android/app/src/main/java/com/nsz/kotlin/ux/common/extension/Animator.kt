package com.nsz.kotlin.ux.common.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

suspend fun Animator.awaitEnd() = suspendCancellableCoroutine<Unit> { cont ->

    // 增加一个处理协程取消的监听器. 如果协程被取消
    // 同时执行动画监听器的 onAnimationCancel() 方法. 取消动画
    cont.invokeOnCancellation { cancel() }

    addListener(
        object : AnimatorListenerAdapter() {

            private var endedSuccessfully = true

            override fun onAnimationCancel(animation: Animator) {
                super.onAnimationCancel(animation)
                // 动画已经被取消. 修改是否成功结束的标志
                endedSuccessfully = false
            }

            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                // 为了在协程恢复后的不发生泄漏. 需要确保移除监听
                animation.removeListener(this)
                if (cont.isActive) {
                    // 如果协程仍处于活跃状态
                    if (endedSuccessfully) {
                        // 并且动画正常结束. 恢复协程
                        cont.resume(Unit)
                    } else {
                        // 否则动画被取消，同时取消协程
                        cont.cancel()
                    }
                }
            }

        }
    )

}