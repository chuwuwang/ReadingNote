package com.nsz.kotlin.aac.architecture.data.binding

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.nsz.kotlin.R
import com.nsz.kotlin.databinding.ActivityAacArchitectureDataBindingRecyclerViewBinding

class DataBindingRecyclerViewActivity : AppCompatActivity() {

    private val list: MutableList<ObservableBean> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityAacArchitectureDataBindingRecyclerViewBinding>(this, R.layout.activity_aac_architecture_data_binding_recycler_view)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = RecyclerViewAdapter(list)

        var observableBean = ObservableBean()
        observableBean.name = System.currentTimeMillis().toString()
        list.add(observableBean)

        observableBean = ObservableBean()
        observableBean.name = System.currentTimeMillis().toString()
        list.add(observableBean)

        observableBean = ObservableBean()
        observableBean.name = System.currentTimeMillis().toString()
        list.add(observableBean)

        Handler().postDelayed(
            { observableBean.name = "12345678" }, 3000
        )
    }

}