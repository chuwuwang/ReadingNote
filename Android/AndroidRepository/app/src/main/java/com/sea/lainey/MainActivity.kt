package com.sea.lainey

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sea.common.http.interceptor.CaptureOkHttpInterceptor
import com.sea.common.http.interceptor.ui.HttpLogsDetailActivity
import com.sea.common.http.interceptor.ui.HttpLogsHelper
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val okHttpInterceptor =
            CaptureOkHttpInterceptor(this)
        val okHttpClient = OkHttpClient.Builder()
            // .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(okHttpInterceptor)
            .build()
        val request = Request.Builder()
            .addHeader("xr", "Ron")
            .addHeader("test", "value")
            // .url("https://www.baidu.com")
            .url("https://www.wanandroid.com/article/list/1/json?userName=xiaoming&userPassword=12345")
            .get()
            .build()
        okHttpClient.newCall(request).enqueue(
            object : Callback {

                override fun onFailure(call: Call, e: IOException) {

                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    runOnUiThread {
                        val httpEntity = HttpLogsHelper.getHttpLogList(this@MainActivity)[0]
                        HttpLogsDetailActivity.startActivity(this@MainActivity, httpEntity)
                    }
                }

            }
        )
    }

}