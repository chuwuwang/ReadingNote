package com.nsz.kotlin.aac.architecture.view.model

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import com.nsz.kotlin.R
import com.nsz.kotlin.ux.common.CommonLog
import kotlinx.android.synthetic.main.activity_aac_architecture_view_model.*

class ViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aac_architecture_view_model)
        initView()
    }

    private fun initView() {
        val model = ViewModelProvider(this)[MyViewModel::class.java]

        model.userData.observe(this,
            Observer {
                val age = it.age
                val name = it.name
                tv_message.text = "name: $name age: $age"
            }
        )

        Transformations.map(model.stringData) {
            val bool = it.endsWith("2") || it.endsWith("4") || it.endsWith("6") || it.endsWith("8")
            var value = it
            if (bool) {
                value += " ktx"
            }
            value
        }.observe(this,
            Observer {
                tv_message_map.text = it
            }
        )

        model.mediatorLiveData.observe(this,
            Observer {
                CommonLog.e("mediatorLiveData $it")
                tv_message_mutable.text = it
            }
        )

        btn_data.setOnClickListener {
            model.getUserInfo()
            model.map()
            model.merge()
        }
    }

}