package com.nsz.kotlin.aac.architecture.room

import androidx.annotation.MainThread

interface LoadUserCallback {

    @MainThread
    fun onUserLoaded(user: User)

    @MainThread
    fun onDataNotAvailable()

}