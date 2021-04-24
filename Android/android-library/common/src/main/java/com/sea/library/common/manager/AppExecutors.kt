package com.sea.library.common.manager

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors {

    private val diskIO: Executor
    private val networkIO: Executor
    private val mainThread: Executor

    private constructor(diskIO: Executor, networkIO: Executor, mainThread: Executor) {
        this.diskIO = diskIO
        this.networkIO = networkIO
        this.mainThread = mainThread
    }

    private constructor() {
        diskIO = Executors.newSingleThreadExecutor()
        networkIO = Executors.newFixedThreadPool(THREAD_COUNT)
        mainThread = MainThreadExecutor()
    }

    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {

        private val looper = Looper.getMainLooper()
        private val mainThreadHandler = Handler(looper)

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }

    }

    private object SingletonHolder {
        val holder = AppExecutors()
    }

    companion object {
        private const val THREAD_COUNT = 3
        val instance = SingletonHolder.holder
    }

}