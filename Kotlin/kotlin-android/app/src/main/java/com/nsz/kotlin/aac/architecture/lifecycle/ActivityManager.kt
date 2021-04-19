package com.nsz.kotlin.aac.architecture.lifecycle

import android.app.Activity
import java.util.*

object ActivityManager {

    private val activityList = LinkedList<Activity>()

    /**
     * push the specified [activity] into the list
     */
    fun pushActivity(activity: Activity) {
        val contains = activityList.contains(activity)
        if (contains) {
            if (activityList.last != activity) {
                activityList.remove(activity)
                activityList.add(activity)
            }
        } else {
            activityList.add(activity)
        }
    }

    /**
     * pop the specified [activity] into the list
     */
    fun popActivity(activity: Activity) {
        activityList.remove(activity)
    }

    fun finishActivity(activity: Activity) {
        activityList.remove(activity)
        activity.finish()
    }

    fun finishActivity(clazz: Class<*>) {
        for (activity in activityList) {
            if (activity.javaClass == clazz) activity.finish()
        }
    }

    fun finishAllActivity() {
        for (activity in activityList) {
            activity.finish()
        }
    }

}