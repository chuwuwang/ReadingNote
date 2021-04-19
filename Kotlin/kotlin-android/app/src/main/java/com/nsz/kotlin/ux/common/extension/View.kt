package com.nsz.kotlin.ux.common.extension

import android.util.Log
import android.view.View
import com.nsz.kotlin.spannable.SpannableStringActivity
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

suspend fun View.awaitNextLayout() = suspendCancellableCoroutine<Unit> { cont ->

    Log.d("nsz", "awaitNextLayout")

    // 这里的 lambda 表达式会被立即调用. 允许我们创建一个监听器
    val listener = object : View.OnLayoutChangeListener {

        override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
            // 先移除监听. 防止协程泄漏
            v.removeOnLayoutChangeListener(this)
            // 唤醒协程. 恢复执行
            cont.resume(Unit)

            Log.d("nsz", "唤醒协程. 恢复执行")
        }

    }
    // 如果协程被取消. 移除该监听
    cont.invokeOnCancellation { removeOnLayoutChangeListener(listener) }

    // 将监听添加到 view 上
    addOnLayoutChangeListener(listener)
    // 这样协程就被挂起了. 除非监听器中的 cont.resume() 方法被调用

    Log.d("nsz", "这样协程就被挂起了. 除非监听器中的 cont.resume() 方法被调用")
}