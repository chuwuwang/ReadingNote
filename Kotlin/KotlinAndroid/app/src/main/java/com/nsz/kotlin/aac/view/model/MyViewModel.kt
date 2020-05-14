package com.nsz.kotlin.aac.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

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