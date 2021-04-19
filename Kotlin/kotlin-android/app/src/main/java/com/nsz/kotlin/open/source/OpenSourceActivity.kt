package com.nsz.kotlin.open.source

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import com.nsz.kotlin.open.source.realm.RealmActivity
import kotlinx.android.synthetic.main.activity_open_source.*
import org.jetbrains.anko.startActivity

class OpenSourceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_source)
        initView()
    }

    private fun initView() {
        mb_realm.setOnClickListener {
            startActivity<RealmActivity>()
        }
    }

}