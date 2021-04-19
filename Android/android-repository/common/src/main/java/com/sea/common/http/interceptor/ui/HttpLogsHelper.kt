package com.sea.common.http.interceptor.ui

import android.annotation.SuppressLint
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sea.common.R
import com.sea.common.http.interceptor.HttpEntity
import com.sea.common.storage.SharedPreferencesHelper
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object HttpLogsHelper {

    @JvmStatic
    fun saveHttpLog(context: Context, entry: HttpEntity) {
        val httpLogList = getHttpLogList(context)
        httpLogList.add(0, entry)
        SharedPreferencesHelper.writeObject(context, "sp_http_logs", "key_http_logs", httpLogList)
    }

    @JvmStatic
    fun saveHttpLogList(context: Context, httpLogList: ArrayList<HttpEntity>) {
        SharedPreferencesHelper.writeObject(context, "sp_http_logs", "key_http_logs", httpLogList)
    }

    @JvmStatic
    fun removeHttpLog(context: Context, entry: HttpEntity) {
        val httpLogList = getHttpLogList(context)
        httpLogList.remove(entry)
        SharedPreferencesHelper.writeObject(context, "sp_http_logs", "key_http_logs", httpLogList)
    }

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun getHttpLogList(context: Context): ArrayList<HttpEntity> {
        val obj = SharedPreferencesHelper.readObject(context, "sp_http_logs", "key_http_logs")
        return if ( obj is MutableList<*> ) {
            obj as ArrayList<HttpEntity>
        } else {
            ArrayList()
        }
    }

    @SuppressLint("SimpleDateFormat")
    @JvmStatic
    fun converterSystemTimeMillis(timeMillis: Long): String {
        val date = Date(timeMillis)
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:dd")
        return simpleDateFormat.format(date)
    }

    @BindingAdapter("header")
    @JvmStatic
    fun converterHeader(view: TextView, headers: HashMap<String, String>) {
        val builder = StringBuilder()
        if (headers == null || headers.size == 0) return
        val context = view.context
        if (context != null) {
            val keys = headers.keys
            val mutableList = mutableListOf<String>()
            for (key in keys) {
                val value = headers[key] as String
                mutableList.add(key)
                builder.append(key)
                builder.append(" : ")
                builder.append(value)
                builder.append("\n")
            }
            val string = builder.toString()
            val spannableString = SpannableString(string)
            for (key in mutableList) {
                val index = spannableString.indexOf(key)
                val textAppearanceSpan = TextAppearanceSpan(context, R.style.HttpHeader_TextAppearance)
                spannableString.setSpan(textAppearanceSpan, index, index + key.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            view.text = spannableString
        }
    }

}