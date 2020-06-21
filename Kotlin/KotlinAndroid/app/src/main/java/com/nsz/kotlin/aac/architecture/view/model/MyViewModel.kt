package com.nsz.kotlin.aac.architecture.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nsz.kotlin.ux.common.CommonLog

class MyViewModel : ViewModel() {

    val userData: MutableLiveData<UserInfo> = MutableLiveData()

    val stringData: MutableLiveData<String> = MutableLiveData()

    private val mergeSee: MutableLiveData<String> = MutableLiveData()
    private val mergeString: MutableLiveData<String> = MutableLiveData()
    val mediatorLiveData: MediatorLiveData<String> = MediatorLiveData()

    init {
        mediatorLiveData.addSource(mergeSee) {
            CommonLog.e("mergeSee")
            mediatorLiveData.value = it
        }
        mediatorLiveData.addSource(mergeString) {
            CommonLog.e("mergeString")
            mediatorLiveData.value = it
        }
    }

    fun getUserInfo() {
        val random = (5..100).random()
        val user = UserInfo("李四", random)
        userData.postValue(user)
    }

    fun map() {
        val millis = System.currentTimeMillis().toString()
        stringData.value = millis
    }

    fun merge() {
        val random = (1..1000).random().toString()
        mergeSee.value = random

        val millis = System.currentTimeMillis().toString()
        mergeString.value = millis
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private val data: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also {
            loadData()
        }
    }

    fun getData(): LiveData<String> {
        return data
    }

    private fun loadData() {
        // Do an asynchronous operation to fetch users.
    }

}