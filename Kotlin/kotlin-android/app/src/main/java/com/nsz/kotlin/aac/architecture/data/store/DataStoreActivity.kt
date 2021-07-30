package com.nsz.kotlin.aac.architecture.data.store

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.lifecycleScope
import com.kotlin.demo.proto.LoginInfo
import kotlinx.coroutines.flow.first

class DataStoreActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "DataStoreActivity"
    }

    private val login: DataStore<LoginInfo.Login> by dataStore(
        fileName = "est.dt",
        serializer = UserInfoSerializer
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    private fun initView() {
        lifecycleScope.launchWhenResumed {
            login.updateData {
                it.toBuilder().setAccount("xxx").build()
            }
            val account = login.data.first().account
            Log.e(TAG, "account: $account")
        }
    }

}