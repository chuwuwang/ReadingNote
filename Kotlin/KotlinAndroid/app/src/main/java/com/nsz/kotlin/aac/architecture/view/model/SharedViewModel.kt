package com.nsz.kotlin.aac.architecture.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    var sharedName: MutableLiveData<String> = MutableLiveData()

    init {
        sharedName.value = "Hello World"
    }

}