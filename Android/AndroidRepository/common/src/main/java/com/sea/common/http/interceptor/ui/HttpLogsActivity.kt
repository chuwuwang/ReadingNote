package com.sea.common.http.interceptor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.sea.common.BR
import com.sea.common.R
import com.sea.common.databinding.HttpActivityLogsBinding
import com.sea.common.databinding.HttpItemLogsRvBinding
import com.sea.common.executors.AppExecutors
import com.sea.common.http.interceptor.HttpEntity
import com.sea.common.listener.OnItemTouchEventListener
import com.sea.common.ui.activity.ImmersiveStatusBarActivity
import java.util.*

class HttpLogsActivity : ImmersiveStatusBarActivity() {

    private val dataList: MutableList<HttpEntity> = mutableListOf()
    private val adapter: HttpLogsAdapter by lazy {
        HttpLogsAdapter(dataList) { _, _, position ->
            val entity = dataList[position]
            HttpLogsDetailActivity.startActivity(this, entity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        setStatusBarColor(R.color.colorLittleBlue)
    }

    private fun initView() {
        val binding = DataBindingUtil.setContentView<HttpActivityLogsBinding>(this, R.layout.http_activity_logs)
        binding.httpRecyclerView.adapter = adapter
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.httpRecyclerView)
    }

    override fun onResume() {
        super.onResume()
        AppExecutors.instance.diskIO().execute {
            val httpLogList = HttpLogsHelper.getHttpLogList(this)
            dataList.clear()
            dataList.addAll(httpLogList)
            runOnUiThread { adapter.notifyDataSetChanged() }
        }
    }

    private val itemTouchHelperCallback = object : ItemTouchHelper.Callback() {

        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            return makeMovementFlags(dragFlags, swipeFlags)
        }

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            adapter.onItemMove(viewHolder.adapterPosition,target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            adapter.onItemRemove(viewHolder.adapterPosition)
        }

    }

    inner class HttpLogsAdapter(
        private var data: MutableList<HttpEntity>,
        private var action: (ViewGroup, View, Int) -> Unit
    ) : RecyclerView.Adapter<HttpLogsViewHolder>(), OnItemTouchEventListener {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HttpLogsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<HttpItemLogsRvBinding>(layoutInflater, R.layout.http_item_logs_rv, parent, false)
            binding.root.setOnClickListener { view ->
                val position = view.tag as Int
                action(parent, view, position)
            }
            return HttpLogsViewHolder(binding)
        }

        override fun getItemCount(): Int  = data.size

        override fun onBindViewHolder(holder: HttpLogsViewHolder, position: Int) {
            val item = data[position]
            holder.itemView.tag = position
            val binding = holder.binding
            binding.setVariable(BR.entity, item)
            binding.executePendingBindings()
        }

        override fun onItemMove(fromPosition: Int, toPosition: Int) {
            AppExecutors.instance.diskIO().execute {
                val httpLogList = HttpLogsHelper.getHttpLogList(baseContext)
                Collections.swap(httpLogList, fromPosition, toPosition)
                HttpLogsHelper.saveHttpLogList(baseContext, httpLogList)
            }
            Collections.swap(data, fromPosition, toPosition)
            notifyItemMoved(fromPosition, toPosition)
        }

        override fun onItemRemove(position: Int) {
            val item = data[position]
            AppExecutors.instance.diskIO().execute { HttpLogsHelper.removeHttpLog(baseContext, item) }
            data.removeAt(position)
            notifyItemRemoved(position)
        }

    }

    inner class HttpLogsViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}