package com.nsz.kotlin.thread

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import kotlinx.android.synthetic.main.activity_launch_scope.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request

class LaunchScopeActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private val url =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1585249997251&di=7366cfcc958d7af6470d111fccf09148&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D1484500186%2C1503043093%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D853"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_scope)
        initView()
    }

    private fun initView() {
        launch(Dispatchers.Main) {
            val bitmap = getImage()
            val async1 = async {
                Bitmap.createBitmap(bitmap, 0, 0, bitmap.width / 2, bitmap.height / 2)
            }
            val async2 = async {
                Bitmap.createBitmap(bitmap, bitmap.width / 2, 0, bitmap.width / 2, bitmap.height / 2)
            }
            val async3 = async {
                Bitmap.createBitmap(bitmap, 0, bitmap.height / 2, bitmap.width / 2, bitmap.height / 2)
            }
            val async4 = async {
                Bitmap.createBitmap(bitmap, bitmap.width / 2, bitmap.height / 2, bitmap.width / 2, bitmap.height / 2)
            }
            iv_1.setImageBitmap(async1.await())
            iv_2.setImageBitmap(async2.await())
            iv_3.setImageBitmap(async3.await())
            iv_4.setImageBitmap(async4.await())
        }
    }

    private suspend fun getImage(): Bitmap = withContext(Dispatchers.IO) {
        val build = Request.Builder().url(url).get().build()
        OkHttpClient().newCall(build).execute().body?.byteStream().use { BitmapFactory.decodeStream(it) }
    }

}