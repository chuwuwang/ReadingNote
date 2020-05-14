package com.nsz.kotlin.aac

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import com.nsz.kotlin.aac.data.binding.DataBindingActivity
import com.nsz.kotlin.aac.data.binding.DataBindingRecyclerViewActivity
import com.nsz.kotlin.aac.live.data.LiveDataActivity
import kotlinx.android.synthetic.main.activity_aac.*
import org.jetbrains.anko.startActivity

class AndroidArchitectureComponentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aac)
        initView()
    }

    private fun initView() {
        mb_live_data.setOnClickListener {
            startActivity<LiveDataActivity>()
        }
        mb_data_binding.setOnClickListener {
            startActivity<DataBindingActivity>()
        }
        mb_data_binding_recycler_view.setOnClickListener {
            startActivity<DataBindingRecyclerViewActivity>()
        }
    }

}