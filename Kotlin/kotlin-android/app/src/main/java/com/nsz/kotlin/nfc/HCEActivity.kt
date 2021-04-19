package com.nsz.kotlin.nfc

import android.content.ComponentName
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.cardemulation.CardEmulation
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.ux.common.CommonLog

class HCEActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setDefaultPaymentApp()
    }

    private fun initView() {

    }

    private fun setDefaultPaymentApp() {
        val adapter = NfcAdapter.getDefaultAdapter(this)
        val cardEmulation = CardEmulation.getInstance(adapter)
        val canonicalName = HCEService::class.java.canonicalName ?: return
        val componentName = ComponentName(applicationContext, canonicalName)
        val isDefaultServiceForCategory = cardEmulation.isDefaultServiceForCategory(componentName, CardEmulation.CATEGORY_PAYMENT)
        if (isDefaultServiceForCategory) {
            CommonLog.e("当前应用是系统默认支付程序")
        } else {
            CommonLog.e("当前应用不是默认支付程序，需手动设置")
            val intent = Intent(CardEmulation.ACTION_CHANGE_DEFAULT)
            intent.putExtra(CardEmulation.EXTRA_CATEGORY, CardEmulation.CATEGORY_PAYMENT)
            intent.putExtra(CardEmulation.EXTRA_SERVICE_COMPONENT, componentName)
            startActivityForResult(intent, 0)
        }
    }

}