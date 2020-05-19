package com.nsz.kotlin.aac

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import com.nsz.kotlin.aac.architecture.ArchitectureActivity
import com.nsz.kotlin.aac.ui.UIActivity
import kotlinx.android.synthetic.main.activity_aac.*
import org.jetbrains.anko.startActivity

class AndroidArchitectureComponentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aac)
        initView()
    }

    private fun initView() {
        mb_ui.setOnClickListener {
            startActivity<UIActivity>()
        }
        mb_behavior.setOnClickListener {

        }
        mb_architecture.setOnClickListener {
            startActivity<ArchitectureActivity>()
        }
        mb_foundation.setOnClickListener {

        }
    }

}