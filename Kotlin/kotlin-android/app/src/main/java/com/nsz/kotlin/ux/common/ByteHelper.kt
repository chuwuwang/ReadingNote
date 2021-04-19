package com.nsz.kotlin.ux.common

import org.bouncycastle.util.encoders.Hex
import java.nio.charset.StandardCharsets

object ByteHelper {

    @JvmStatic
    fun bytes2HexString(bytes: ByteArray): String {
        try {
            return Hex.toHexString(bytes).toUpperCase()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    @JvmStatic
    fun hexString2Bytes(hexString: String): ByteArray {
        try {
            return Hex.decode(hexString)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ByteArray(0)
    }

    @JvmStatic
    fun hexString2String(hexString: String): String {
        try {
            val bytes = hexString2Bytes(hexString)
            return String(bytes, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

}