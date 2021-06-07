package com.sea.library.common.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.app.ComponentActivity
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding

inline fun <reified T : Activity> Activity.openActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

inline fun <reified T : Activity> Activity.openActivity(block: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java)
    intent.block()
    startActivity(intent)
}

inline fun <reified T : Activity> openActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}

inline fun <reified VB : ViewBinding> ComponentActivity.binding() = lazy {
    inflateBinding<VB>(layoutInflater).also {
        setContentView(it.root)
        if (this is ViewDataBinding) {
            lifecycleOwner = this@binding
        }
    }
}

