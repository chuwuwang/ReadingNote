package com.nsz.kotlin.aac.architecture.data.binding

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.nsz.kotlin.R
import com.nsz.kotlin.aac.architecture.live.data.Student
import com.nsz.kotlin.databinding.ActivityAacArchitectureDataBindingBinding

class DataBindingActivity : AppCompatActivity() {

    private val user = User(
        ObservableField<String>("Shanghai"),
        ObservableInt(10)
    )
    private val student = Student("杨大爷", 22)
    private val observableBean = ObservableBean()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityAacArchitectureDataBindingBinding>(this, R.layout.activity_aac_architecture_data_binding)
        binding.user = user
        binding.student = student
        binding.observableBean = observableBean

        binding.bol = true
        binding.activity = this

        binding.fx = 2f

        loopData()

        Thread {
            while (true) {
                try {

                    Thread.sleep(1200)

                    Log.e("lz", "binding.fx: ${binding.fx}")

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun loopData() {
        Thread {
            while (true) {
                try {
                    val temp = System.currentTimeMillis().toString()

                    student.name = temp

                    user.name.set(temp)

                    observableBean.name = temp

                    Thread.sleep(500)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    fun onClickForEvent(view: View) {
        Log.e("lz", "onClickForEvent")
    }

    fun onChangeUI(view: View, bool: Boolean) {
        Log.e("lz", "onChangeUI $bool")
    }

}