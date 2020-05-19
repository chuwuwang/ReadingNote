package com.nsz.kotlin.aac.ui.view.pager2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import kotlinx.android.synthetic.main.activity_acc_ui_pager2.*
import org.jetbrains.anko.startActivity

class ViewPager2Activity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acc_ui_pager2)
        initView()
    }

    private fun initView() {
        mb_vertical.setOnClickListener(this)
        mb_fragment.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            mb_vertical -> startActivity<ViewPager2VerticalActivity>()
        }
    }

}