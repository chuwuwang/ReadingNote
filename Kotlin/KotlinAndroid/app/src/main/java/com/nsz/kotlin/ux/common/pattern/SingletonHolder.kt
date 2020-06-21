package com.nsz.kotlin.ux.common.pattern

class SingletonHolder<out T, in A>(private var creator: (A) -> T) {

    @Volatile
    private var instance: T ? = null

    fun getInstance(arg: A): T = instance ?: synchronized(this) {
        instance ?: creator(arg).apply { instance = this }
    }

}