package com.nsz.kotlin.thread

import kotlinx.coroutines.*

fun firstDemo1() {
    val scope = CoroutineScope(Dispatchers.Default)
    scope.launch {
        delay(3000L)
        println("Hello")
    }
    repeat(6) {
        println("Hello $it")
    }
    println("World")
    Thread.sleep(5000L)
}

fun main() {

    demo2()
}

fun demo2() {
    GlobalScope.launch {
        val result1 = GlobalScope.async {
            getResult1()
        }
        val result2 = GlobalScope.async {
            getResult2()
        }
        result1.join()
        result2.join()
        println("World")
        Thread.sleep(5000L)
    }
}

private suspend fun getResult1(): Int {
    delay(3000)
    println("getResult1")
    return 1
}

private suspend fun getResult2(): Int {
    delay(4000)
    println("getResult2")
    return 2
}