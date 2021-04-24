package com.sea.library.common.helper

import android.os.SystemClock
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun timeMillisToString(timeMillis: Long, format: String = "yyyy-MM-dd HH:mm:ss"): String {
        val simpleDateFormat = SimpleDateFormat(format)
        val date = Date(timeMillis)
        return simpleDateFormat.format(date)
    }

    val microTime: Long
        get() = System.currentTimeMillis() * 1000 + SystemClock.elapsedRealtimeNanos() / 1000 % 1000

}