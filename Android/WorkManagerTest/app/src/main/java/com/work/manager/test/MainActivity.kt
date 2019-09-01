package com.work.manager.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*

class MainActivity : AppCompatActivity() {

    private val tag = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
    }

    private fun initData() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)      // 在充电时执行
            .setRequiresDeviceIdle(true)    // 在待机状态下执行 需要 API 23
            .setRequiresStorageNotLow(true) // 不在存储容量不足时执行
            .setRequiresBatteryNotLow(true) // 不在电量不足时执行
            .setRequiredNetworkType(NetworkType.CONNECTED)  // 网络状态
            .build()

        val data = Data.Builder()
            .putString("code", "123456")
            .putString("name", "beijing")
            .build()

        val request = OneTimeWorkRequestBuilder<MyWorker>()
            .setConstraints(constraints)
            .addTag("A")
            .setInputData(data)
            .build()

        val uuid = request.id;

        WorkManager.getInstance(this).enqueue(request)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(uuid)
            .observe(this,
                Observer { workInfo ->
                    if (workInfo != null && workInfo.state.isFinished) {
                        Log.e(tag, "SUCCEEDED")
                    }
                }
            )

    }


}
