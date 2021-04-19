package com.nsz.kotlin.aac.architecture.foreground.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import com.nsz.kotlin.R
import com.nsz.kotlin.ux.common.CommonLog

class ForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        CommonLog.d("onBind")
        return null
    }

    override fun onCreate() {
        super.onCreate()
        CommonLog.d("onCreate")
        val notification = createNotification()
        startForeground(100, notification) // 设为0 不显示通知

        val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CommonLog.d("onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        stopForeground(true)
        super.onDestroy()
    }

    private fun createNotification(): Notification {
        val builder = Notification.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("KTX Service")
            .setContentText("service running")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel("ktx_id", "ktx", NotificationManager.IMPORTANCE_DEFAULT)
            channel.setShowBadge(true)  // 显示logo
            channel.enableLights(true)  // 设置提示灯
            channel.lightColor = Color.RED  // 设置提示灯颜色
            channel.description = "bottom bar notification" // 设置描述
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC // 设置锁屏可见 VISIBILITY_PUBLIC=可见
            notificationManager.createNotificationChannel(channel)
            builder.setChannelId("ktx_id")
        }
        return builder.build()
    }

    private val phoneStateListener = object : PhoneStateListener() {

        override fun onCallStateChanged(state: Int, phoneNumber: String?) {
            super.onCallStateChanged(state, phoneNumber)
            CommonLog.d("state: $state phoneNumber: $phoneNumber")
            when (state) {
                TelephonyManager.CALL_STATE_IDLE -> {
                    // 待机 即无电话时 挂断时触发
                }
                TelephonyManager.CALL_STATE_RINGING -> {
                    // 响铃 来电时触发
                }
                TelephonyManager.CALL_STATE_OFFHOOK -> {
                    // 摘机 接听或打电话时触发
                }
                else -> {

                }
            }
        }

    }

}