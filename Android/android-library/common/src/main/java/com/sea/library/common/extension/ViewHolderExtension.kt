package com.sea.library.common.extension

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

inline fun <reified VB : ViewBinding> bindingViewHolder(parent: ViewGroup) {
    val binding = inflateBinding<VB>(parent)
    BindingViewHolder(binding)
}

inline fun <VB : ViewBinding> BindingViewHolder<VB>.onBinding(crossinline action: VB.(Int) -> Unit) = apply { binding.action(adapterPosition) }

inline fun <VB : ViewBinding> BindingViewHolder<VB>.onItemClick(crossinline action: VB.(Int) -> Unit) = apply { itemView.setOnClickListener { binding.action(adapterPosition) } }

class BindingViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)