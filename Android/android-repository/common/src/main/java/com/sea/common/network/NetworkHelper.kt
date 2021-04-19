package com.sea.common.network

import android.content.Context
import android.net.ConnectivityManager

object NetworkHelper {

    @JvmStatic
    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isAvailable
    }

}