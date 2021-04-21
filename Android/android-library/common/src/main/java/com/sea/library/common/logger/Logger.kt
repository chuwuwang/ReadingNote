package com.sea.library.common.logger

import android.util.Log

object Logger {

    const val VERBOSE = 1
    const val DEBUG = 2
    const val INFO = 3
    const val WARN = 4
    const val ERROR = 5
    const val NONE = 6

    private var printLevel = VERBOSE

    @JvmStatic
    fun v(tag: String, msg: String) {
        log(VERBOSE, tag, msg)
    }

    @JvmStatic
    fun d(tag: String, msg: String) {
        log(DEBUG, tag, msg)
    }

    @JvmStatic
    fun i(tag: String, msg: String) {
        log(INFO, tag, msg)
    }

    @JvmStatic
    fun w(tag: String, msg: String) {
        log(WARN, tag, msg)
    }

    @JvmStatic
    fun e(tag: String, msg: String) {
        log(ERROR, tag, msg)
    }

    private fun log(type: Int, tag: String, message: String) {
        val index = 4
        val stackTraceArrays = Thread.currentThread().stackTrace
        val stackTrace = stackTraceArrays[index]
        val fileName = stackTrace.fileName
        val className = stackTrace.className
        val lineNumber = stackTrace.lineNumber
        val methodName = stackTrace.methodName
        val builder = StringBuilder()
        builder.append(className)
            .append(".")
            .append(methodName)
            .append("()")
            .append(" (")
            .append(fileName)
            .append(":")
            .append(lineNumber)
            .append(") ")
        val prefixString = builder.toString()
        val size = 3800
        var printString = message
        val length = printString.length
        if (length > size) {
            while (printString.length > size) {
                val substring = printString.substring(0, size)
                printString = printString.replace(substring, "")
                print(type, tag, substring, prefixString)
            }
        }
        print(type, tag, printString, prefixString)
    }

    private fun print(type: Int, tag: String, message: String, prefix: String) {
        val log = prefix + message
        when (type) {
            INFO -> if (printLevel <= INFO) Log.i(tag, log)
            WARN -> if (printLevel <= WARN) Log.w(tag, log)
            DEBUG -> if (printLevel <= DEBUG) Log.d(tag, log)
            ERROR -> if (printLevel <= ERROR) Log.e(tag, log)
            VERBOSE -> if (printLevel <= VERBOSE) Log.v(tag, log)
        }
    }

}