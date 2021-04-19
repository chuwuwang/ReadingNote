package com.nsz.kotlin.aac.architecture.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nsz.kotlin.R
import com.nsz.kotlin.ux.common.CommonLog

class CheeseAdapter : PagedListAdapter<Cheese, CheeseAdapter.CheeseViewHolder>(diffCallback) {

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<Cheese>() {

            override fun areItemsTheSame(oldItem: Cheese, newItem: Cheese): Boolean {
                CommonLog.d("areItemsTheSame")
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Cheese, newItem: Cheese): Boolean {
                CommonLog.d("areContentsTheSame")
                return oldItem == newItem
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_acc_architecture_paging_rv, parent, false)
        return CheeseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindTo(item)
    }

    inner class CheeseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameView: TextView = itemView.findViewById(R.id.tv_name)

        var cheese: Cheese ? = null

        fun bindTo(item: Cheese?) {
            this.cheese = item
            if (item != null) {
                nameView.text = item.name
            } else {
                nameView.text = ""
            }
        }

    }

}