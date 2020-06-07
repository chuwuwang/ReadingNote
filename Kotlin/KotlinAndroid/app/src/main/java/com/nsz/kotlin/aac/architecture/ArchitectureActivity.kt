package com.nsz.kotlin.aac.architecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import com.nsz.kotlin.aac.architecture.data.binding.DataBindingActivity
import com.nsz.kotlin.aac.architecture.data.binding.DataBindingRecyclerViewActivity
import com.nsz.kotlin.aac.architecture.lifecycle.LifecycleActivity
import com.nsz.kotlin.aac.architecture.live.data.LiveDataActivity
import com.nsz.kotlin.aac.architecture.room.RoomActivity
import com.nsz.kotlin.aac.architecture.view.model.ViewModelActivity
import kotlinx.android.synthetic.main.activity_aac_architecture.*
import org.jetbrains.anko.startActivity

class ArchitectureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aac_architecture)
        initView()
    }

    private fun initView() {
        mb_room.setOnClickListener {
            startActivity<RoomActivity>()
        }
        mb_live_data.setOnClickListener {
            startActivity<LiveDataActivity>()
        }
        mb_lifecycle.setOnClickListener {
            startActivity<LifecycleActivity>()
        }
        mb_view_model.setOnClickListener {
            startActivity<ViewModelActivity>()
        }
        mb_data_binding.setOnClickListener {
            startActivity<DataBindingActivity>()
        }
        mb_data_binding_recycler_view.setOnClickListener {
            startActivity<DataBindingRecyclerViewActivity>()
        }
    }

}