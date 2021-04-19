package com.nsz.kotlin.nfc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import kotlinx.android.synthetic.main.activity_nfc.*
import org.jetbrains.anko.startActivity

class NFCActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc)
        initView()
    }

    private fun initView() {
        mb_hce.setOnClickListener {
            startActivity<HCEActivity>()
        }
        mb_read_m1_card.setOnClickListener {
            startActivity<ReaderM1CardActivity>()
        }
    }

}