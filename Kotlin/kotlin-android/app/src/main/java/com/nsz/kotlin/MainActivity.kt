package com.nsz.kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.demo.proto.LoginInfo
import com.nsz.kotlin.aac.AndroidArchitectureComponentsActivity
import com.nsz.kotlin.nfc.NFCActivity
import com.nsz.kotlin.open.source.OpenSourceActivity
import com.nsz.kotlin.spannable.SpannableStringActivity
import com.nsz.kotlin.storage.StorageActivity
import com.nsz.kotlin.thread.LaunchScopeActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        LoginInfo.Login.newBuilder().setAccount("01").setPassword("123456").build()

        mb_scoped_storage.setOnClickListener {
            val intent = Intent(this@MainActivity, StorageActivity::class.java)
            startActivity(intent)
        }
        mb_thread.setOnClickListener {
            startActivity<LaunchScopeActivity>()
        }
        mb_spannable_string.setOnClickListener {
            startActivity<SpannableStringActivity>()
        }
        mb_aac.setOnClickListener {
            startActivity<AndroidArchitectureComponentsActivity>()
        }
        mb_nfc.setOnClickListener {
            startActivity<NFCActivity>()
        }
        mb_open_source.setOnClickListener {
            startActivity<OpenSourceActivity>()
        }
    }

}