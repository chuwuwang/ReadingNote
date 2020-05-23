package com.nsz.kotlin.common

import org.bouncycastle.util.encoders.Hex
import java.nio.charset.StandardCharsets

object ByteHelper {

    @JvmStatic
    fun bytes2HexString(bytes: ByteArray): String {
        return Hex.toHexString(bytes).toUpperCase()
    }

    @JvmStatic
    fun hexString2Bytes(hexString: String): ByteArray {
        try {
            return Hex.decode(hexString)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ByteArray(16)
    }

    @JvmStatic
    fun hexString2String(hexString: String): String {
        var result = ""
        try {
            val bytes = hexString2Bytes(hexString)
            result = String(bytes, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

}