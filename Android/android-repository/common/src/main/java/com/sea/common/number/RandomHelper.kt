package com.sea.common.number

import java.util.*

object RandomHelper {

    fun getRandomHexString(size: Int): String {
        val random = Random()
        val sb = StringBuffer()
        while (sb.length < size) {
            val randomValue = random.nextInt(15) // 0 - 15
            val hexString = Integer.toHexString(randomValue)
            sb.append(hexString)
        }
        return sb.toString().toUpperCase()
    }

}