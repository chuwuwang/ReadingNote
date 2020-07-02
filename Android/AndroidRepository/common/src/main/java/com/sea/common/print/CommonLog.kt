package com.sea.common.print

import android.util.Log

object CommonLog {

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

    fun v(tag: String, msg: String) {
        if (LEVEL <= V) log(V, tag, msg)
    }

    fun d(tag: String, msg: String) {
        if (LEVEL <= D) log(D, tag, msg)
    }

    fun i(tag: String, msg: String) {
        if (LEVEL <= I) log(I, tag, msg)
    }

    fun w(tag: String, msg: String) {
        if (LEVEL <= W) log(W, tag, msg)
    }

    fun e(tag: String, msg: String) {
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
        var logString = sb.toString()

        val size = 4000
        val length = logString.length
        if (length > size) {
            while (logString.length > size) {
                val substring = logString.substring(0, size)
                logString = logString.replace(substring, "")
                print(type, tag, substring)
            }
        }
        print(type, tag, logString)
    }

    private fun print(type: Int, tag: String, msg: String) {
        when (type) {
            V -> Log.v(tag, msg)
            D -> Log.d(tag, msg)
            I -> Log.i(tag, msg)
            W -> Log.w(tag, msg)
            E -> Log.e(tag, msg)
            else -> Log.i(tag, msg)
        }
    }

}