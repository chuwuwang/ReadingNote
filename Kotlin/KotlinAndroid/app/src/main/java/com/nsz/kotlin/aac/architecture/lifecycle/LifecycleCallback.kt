package com.nsz.kotlin.aac.architecture.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

class LifecycleCallback : Application.ActivityLifecycleCallbacks {

    companion object {
        private const val TAG = "LifecycleCallback"
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        val clazz = activity.javaClass.name
        Log.d(TAG, "onActivityCreated $clazz")
        ActivityManager.pushActivity(activity)
    }

    override fun onActivityStarted(activity: Activity) {
        val clazz = activity.javaClass.name
        Log.d(TAG, "onActivityStarted $clazz")
    }

    override fun onActivityResumed(activity: Activity) {
        val clazz = activity.javaClass.name
        Log.d(TAG, "onActivityResumed $clazz")
    }

    override fun onActivityPaused(activity: Activity) {
        val clazz = activity.javaClass.name
        Log.d(TAG, "onActivityPaused $clazz")
    }

    override fun onActivityStopped(activity: Activity) {
        val clazz = activity.javaClass.name
        Log.d(TAG, "onActivityStopped $clazz")
    }

    override fun onActivityDestroyed(activity: Activity) {
        val clazz = activity.javaClass.name
        Log.d(TAG, "onActivityDestroyed $clazz")
        ActivityManager.popActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
        val clazz = activity.javaClass.name
        Log.d(TAG, "onActivitySaveInstanceState $clazz")
    }

}