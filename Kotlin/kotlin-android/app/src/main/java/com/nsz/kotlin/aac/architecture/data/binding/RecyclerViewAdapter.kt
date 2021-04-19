package com.nsz.kotlin.aac.architecture.data.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.nsz.kotlin.BR
import com.nsz.kotlin.R
import com.nsz.kotlin.databinding.RecyclerViewAdapterDataBinding

class RecyclerViewAdapter(var data: MutableList<ObservableBean>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RecyclerViewAdapterDataBinding>(layoutInflater, R.layout.item_acc_architecture_data_binding_rv, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val binding = holder.binding
        binding.setVariable(BR.observableBean, item)
        binding.executePendingBindings()
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}