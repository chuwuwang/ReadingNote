package com.nsz.kotlin.aac.ui.animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import com.nsz.kotlin.aac.ui.animation.interpolator.BounceInterpolatorActivity
import kotlinx.android.synthetic.main.activity_aac_ui_animation.*
import org.jetbrains.anko.startActivity

class AnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aac_ui_animation)
        initView()
    }

    private fun initView() {
        mb_bounce_interpolator.setOnClickListener {
            startActivity<BounceInterpolatorActivity>()
        }
    }

}