package com.nsz.kotlin.aac.architecture.data.binding

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

data class User(var name: ObservableField<String>, var age: ObservableInt)