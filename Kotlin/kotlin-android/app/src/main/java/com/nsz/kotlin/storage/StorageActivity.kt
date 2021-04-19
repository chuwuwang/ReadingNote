package com.nsz.kotlin.storage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import kotlinx.android.synthetic.main.activity_storage.*
import org.jetbrains.anko.startActivity

class StorageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)
        initView()
    }

    private fun initView() {
        mb_saf.setOnClickListener { startActivity<SAFActivity>() }
        mb_internal_and_external.setOnClickListener { startActivity<ScopedStorageActivity>() }
    }

}