package com.nsz.kotlin.aac.ui.animation.interpolator

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import kotlinx.android.synthetic.main.activity_aac_ui_animation_interpolator.*

class BounceInterpolatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aac_ui_animation_interpolator)
        initView()
    }

    private fun initView() {
        button.setOnClickListener {
            val myAnim = AnimationUtils.loadAnimation(this, R.anim.interpolator_bounce)

            // Use bounce interpolator with amplitude 0.2 and frequency 20
            // val interpolator = BounceInterpolator(0.5, 12.0)
            val interpolator = BounceInterpolator()
            myAnim.interpolator = interpolator

            button.startAnimation(myAnim)
        }
    }

}