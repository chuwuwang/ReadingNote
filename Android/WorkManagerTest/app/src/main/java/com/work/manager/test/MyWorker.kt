package com.work.manager.test

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    private val tag = javaClass.simpleName

    override fun doWork(): Result {
        Log.e(tag, "doWork")

        val code = inputData.getString("code")
        val name = inputData.getString("name")
        Log.e(tag, "code:$code name:$name")
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        Log.e(tag, "onStopped")
    }


}