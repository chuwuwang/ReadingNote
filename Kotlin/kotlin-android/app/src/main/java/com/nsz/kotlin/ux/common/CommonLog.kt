package com.nsz.kotlin.ux.common

import android.util.Log

object CommonLog {

    private const val TAG = "KA"

    private const val V = 1
    private const val D = 2
    private const val I = 3
    private const val W = 4
    private const val E = 5
    private const val N = 6

    private var LEVEL = V

    fun setLevel(Level: Int) {
        LEVEL = Level
    }

    fun v(msg: String, tag: String = TAG) {
        if (LEVEL <= V) log(V, tag, msg)
    }

    @JvmStatic
    fun d(msg: String) {
        d(msg, TAG)
    }

    fun d(msg: String, tag: String = TAG) {
        if (LEVEL <= D) log(D, tag, msg)
    }

    fun i(msg: String, tag: String = TAG) {
        if (LEVEL <= I) log(I, tag, msg)
    }

    fun w(msg: String, tag: String = TAG) {
        if (LEVEL <= W) log(W, tag, msg)
    }

    @JvmStatic
    fun e(msg: String) {
        e(msg, TAG)
    }

    fun e(msg: String, tag: String = TAG) {
        if (LEVEL <= E) log(E, tag, msg)
    }

    private fun log(type: Int, tag: String, msg: String) {
        val stackTrace = Thread.currentThread().stackTrace
        val index = 5
        val className = stackTrace[index].fileName
        var methodName = stackTrace[index].methodName
        val lineNumber = stackTrace[index].lineNumber
        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1)
        val sb = StringBuilder()
        sb.append("[ (")
            .append(className)
            .append(":")
            .append(lineNumber)
            .append(") # ")
            .append(methodName)
            .append(" ] ")
        sb.append(msg)
        val logStr = sb.toString()
        when (type) {
            V -> Log.v(tag, logStr)
            D -> Log.d(tag, logStr)
            I -> Log.i(tag, logStr)
            W -> Log.w(tag, logStr)
            E -> Log.e(tag, logStr)
            else -> Log.e(tag, logStr)
        }
    }

}