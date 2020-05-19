package com.nsz.kotlin

import android.app.Application
import com.nsz.kotlin.aac.architecture.lifecycle.LifecycleCallback

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val lifecycleCallback = LifecycleCallback()
        registerActivityLifecycleCallbacks(lifecycleCallback)
    }

}