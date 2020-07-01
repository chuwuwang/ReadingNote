package com.sea.common.extension

val String.isValid: Boolean
    get() = this != null && isNotBlank()