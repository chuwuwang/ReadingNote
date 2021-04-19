package com.nsz.kotlin.aac.architecture.live.data

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nsz.kotlin.R
import kotlinx.android.synthetic.main.activity_aac_architecture_live_data.*

class LiveDataActivity : AppCompatActivity() {

    private val initLiveData = MutableLiveData("hello")
    private val periodLiveDat = MutableLiveData<String>()
    private val studentLiveData = MutableLiveData<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aac_architecture_live_data)
        initView()
        loopData()
    }

    private fun loopData() {
        Thread {
            while (true) {
                val time = System.currentTimeMillis()
                val vx = "li $time"
                val student = Student(vx, 12)
                studentLiveData.postValue(student)
            }
        }.start()
        Thread {
            while (true) {
                val vx = "xyz " + System.currentTimeMillis()
                periodLiveDat.postValue(vx)
                Thread.sleep(1500)
            }
        }.start()
        Handler().postDelayed(
            {
                initLiveData.value = "world"
            }, 1000
        )
    }

    private fun initView() {
        initLiveData.observe(this,
            Observer {
                Log.d("lz", "LiveData initLiveData: $it")
                tv_live_data_1.text = it
            }
        )
        periodLiveDat.observeForever(observeForever)
        studentLiveData.observe(this,
            Observer { value ->
                val age = value.age
                val name = value.name
                tv_live_data_2.text = "name: $name age: $age"
            }
        )
    }

    private val observeForever = Observer<String> {
        Log.d("lz", "LiveData observeForever: $it")
        tv_live_data_3.text = it
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onDestroy() {
        super.onDestroy()
        periodLiveDat.removeObserver(observeForever)
    }

}