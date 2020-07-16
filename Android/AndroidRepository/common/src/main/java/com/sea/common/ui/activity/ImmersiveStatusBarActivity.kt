package com.sea.common.ui.activity

import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ktx.immersionBar

open class ImmersiveStatusBarActivity : AppCompatActivity() {

    open val layoutId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layoutId != 0) {
            setContentView(layoutId)
        }
        initImmersionBar()
    }

    open fun initImmersionBar() {

    }

    fun setStatusBarColor(view: View) {
        setStatusBarColor(view, false)
    }

    fun setStatusBarColor(@ColorRes statusBarColor: Int) {
        setStatusBarColor(statusBarColor, false)
    }

    fun setStatusBarColor(view: View, keyboardEnable: Boolean) {
        immersionBar {
            titleBar(view)
            keyboardEnable(keyboardEnable)
        }
    }

    fun setStatusBarColor(@ColorRes statusBarColor: Int, keyboardEnable: Boolean) {
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(statusBarColor)
            keyboardEnable(keyboardEnable)
        }
    }

}