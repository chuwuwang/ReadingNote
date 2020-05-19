package com.nsz.kotlin.aac.ui.view.pager2

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.nsz.kotlin.R
import kotlinx.android.synthetic.main.activity_acc_ui_pager2_vertical.*

class ViewPager2VerticalActivity : AppCompatActivity() {

    private val list =
        mutableListOf("#FFFF00", "#FF0000", "#AAFF00", "#FF44FF", "#EEFFEE", "#EEE000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acc_ui_pager2_vertical)
        initView()
    }

    private fun initView() {
        list.add("")
        val pagerAdapter = PagerAdapter()
        view_pager2.adapter = pagerAdapter

        radio_group.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.rb_vertical -> view_pager2.orientation = ViewPager2.ORIENTATION_VERTICAL
                R.id.rb_horizontal -> view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }
        }

        // 禁止滑动
        cb_is_user_input_enabled.setOnCheckedChangeListener { _, b ->
            view_pager2.isUserInputEnabled = b
        }
    }

    inner class PagerAdapter : RecyclerView.Adapter<PagerAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerAdapter.ViewHolder {
            val view = View.inflate(parent.context, R.layout.item_acc_ui_pager2_vertical, null)
            view.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: PagerAdapter.ViewHolder, position: Int) {
            val color = list[position]
            if (color !== "") {
                val parseColor = Color.parseColor(color)
                holder.view.setBackgroundColor(parseColor)
            }
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val view: View = itemView.findViewById(R.id.view_content)
        }

    }

}