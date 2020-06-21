package com.nsz.kotlin.aac.architecture.paging

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.nsz.kotlin.R
import com.nsz.kotlin.ux.common.CommonLog
import kotlinx.android.synthetic.main.activity_aac_architecture_paging.*

class PagingActivity : AppCompatActivity() {

    private val viewModel by viewModels<CheeseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aac_architecture_paging)
        initView()
    }

    private fun initView() {
        val cheeseAdapter = CheeseAdapter()
        recycler_view.adapter = cheeseAdapter

        viewModel.allCheeseList.observe(this,
            Observer {
                cheeseAdapter.submitList(it)
            }
        )

        btn_add.setOnClickListener {
            val inputText = edit_input.text.toString()
            val notBlank = inputText.isNotBlank()
            if (notBlank) {
                viewModel.insert(inputText)
                edit_input.setText("")
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recycler_view)
    }

    private val itemTouchHelperCallback = object : ItemTouchHelper.Callback() {

        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            CommonLog.e("getMovementFlags")
            return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        }

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            CommonLog.e("onMove")
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            CommonLog.e("onSwiped")
            (viewHolder as CheeseAdapter.CheeseViewHolder).cheese?.let {
                viewModel.remove(it)
            }
        }

    }

}