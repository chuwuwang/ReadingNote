package com.nsz.kotlin.aac.architecture.lifecycle

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.nsz.kotlin.ux.common.CommonLog
import kotlin.properties.Delegates

class LifecycleHandler : Handler, LifecycleObserver {

    companion object {
        private const val TAG = "LifecycleHandler"
    }

    private var lifecycleOwner: LifecycleOwner by Delegates.notNull()

    constructor(owner: LifecycleOwner) {
        lifecycleOwner = owner
        addObserver()
    }

    constructor(looper: Looper, owner: LifecycleOwner) : super(looper) {
        lifecycleOwner = owner
        addObserver()
    }

    constructor(callback: Callback, owner: LifecycleOwner) : super(callback) {
        lifecycleOwner = owner
        addObserver()
    }

    constructor(looper: Looper, callback: Callback, owner: LifecycleOwner) : super(
        looper,
        callback
    ) {
        lifecycleOwner = owner
        addObserver()
    }

    private fun addObserver() {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        CommonLog.e("LifecycleHandler onDestroy")
        removeCallbacksAndMessages(null)
        lifecycleOwner.lifecycle.removeObserver(this)
    }

}