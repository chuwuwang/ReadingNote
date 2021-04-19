package com.nsz.kotlin.aac.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import com.nsz.kotlin.aac.ui.animation.AnimationActivity
import com.nsz.kotlin.aac.ui.view.pager2.ViewPager2Activity
import kotlinx.android.synthetic.main.activity_aac_ui.*
import org.jetbrains.anko.startActivity

class UIActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aac_ui)
        initView()
    }

    private fun initView() {
        mb_animation.setOnClickListener {
            startActivity<AnimationActivity>()
        }
        mb_transitions.setOnClickListener {

        }
        mb_fragment.setOnClickListener {

        }
        mb_view_pager2.setOnClickListener {
            startActivity<ViewPager2Activity>()
        }
    }

}