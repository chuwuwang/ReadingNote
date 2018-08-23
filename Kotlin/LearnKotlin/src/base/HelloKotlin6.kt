package base

import kotlin.test.assertTrue

// user-site variance(type projection) 类型投影

// 数组的浅拷贝
fun copy(from: Array<out Any>, to: Array<Any>) {
    assertTrue(from.size == to.size)

    for (i in from.indices) {
        to[i] = from[i]
    }
}

fun main(args: Array<String>) {
    val from: Array<Int> = arrayOf(1, 2, 3, 4)
    val to: Array<Any> = Array<Any>(4, { it -> "hello $it" })
    for (item in to) {
        println(item)
    }

    copy(from, to)

    println("--------------")

    val array: Array<String> = Array<String>(4, { it -> "hello $it" })
    for (item in array) {
        println(item)
    }

    println("--------------")

    setValue(array, 1, "world")

    for (item in array) {
        println(item)
    }
}

fun setValue(to: Array<String>, index: Int, value: String) {
    to[index] = value
}