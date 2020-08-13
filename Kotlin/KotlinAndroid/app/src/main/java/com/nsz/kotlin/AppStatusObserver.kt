package com.nsz.kotlin

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class AppStatusObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        // 只会被调用一次
        Log.d(TAG, "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        // 当程序在前台出现时被调用
        Log.d(TAG, "onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        // 当程序在前台出现时被调用
        Log.d(TAG, "onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        // 当程序退出到后台时被调用
        Log.d(TAG, "onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        // 当程序退出到后台时被调用
        Log.d(TAG, "onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        // 永远不会被调用
        Log.d(TAG, "onDestroy")
    }

    companion object {
        private const val TAG = "AppStatusObserver"
    }

}