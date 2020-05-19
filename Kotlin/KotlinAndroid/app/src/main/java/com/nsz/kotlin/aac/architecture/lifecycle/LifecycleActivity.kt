package com.nsz.kotlin.aac.architecture.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LifecycleHandler(this)
    }

}