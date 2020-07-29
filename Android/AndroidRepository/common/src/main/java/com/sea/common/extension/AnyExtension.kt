package com.sea.common.extension

val String.isValid: Boolean
    get() = this != null && isNotBlank()

val String.notEmptyString: String
    get() = if (isValid) this else ""