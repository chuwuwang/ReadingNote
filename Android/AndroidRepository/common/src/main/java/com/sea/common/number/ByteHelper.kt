package com.sea.common.number

import org.bouncycastle.util.encoders.Hex
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset
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
        return ByteArray(0)
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

    @JvmStatic
    fun hexString2GBKString(hexString: String): String {
        var result = ""
        try {
            val charset = Charset.forName("GBK")
            val bytes = hexString2Bytes(hexString)
            result = String(bytes, charset)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun bcdString2Bytes(string: String): ByteArray {
        var data = ""
        if (string.length % 2 != 0) {
            data = "0"
        }
        data += string
        val bos = ByteArrayOutputStream()
        val arrays = data.toCharArray()
        var i = 0
        while (i < arrays.size) {
            val high = (arrays[i] - 48).toInt()
            val low = (arrays[i + 1] - 48).toInt()
            bos.write(high shl 4 or low)
            i += 2
        }
        return bos.toByteArray()
    }

}