package com.sea.common.storage

import android.content.Context
import org.apache.commons.codec.binary.Base64
import java.io.*

object SharedPreferencesHelper {

    @JvmStatic
    fun readObject(context: Context, fileName: String, key: String): Any {
        val preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        try {
            val value = preferences.getString(key, "")
            if (value != null && value.trim().length > 2) {
                val bytes = value.toByteArray()
                val base64Bytes = Base64.decodeBase64(bytes)
                ByteArrayInputStream(base64Bytes).use { bis ->
                    ObjectInputStream(bis).use {
                        return it.readObject()
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return Any()
    }

    @JvmStatic
    fun <T : Serializable> writeObject(context: Context, fileName: String, key: String, obj: T) {
        val preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        try {
            ByteArrayOutputStream().use { bos ->
                ObjectOutputStream(bos).use {
                    it.writeObject(obj)
                    val bytes = bos.toByteArray()
                    val base64Bytes = Base64.encodeBase64(bytes)
                    val base64String = String(base64Bytes)
                    val editor = preferences.edit()
                    editor.putString(key, base64String)
                    editor.apply()
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}