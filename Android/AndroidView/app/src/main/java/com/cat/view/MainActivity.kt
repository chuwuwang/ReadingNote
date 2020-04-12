package com.cat.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cat.view.draw1.Draw1Activity

class MainActivity : AppCompatActivity() {

    private val list = mutableListOf<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        baseContext
    }

    private fun initView() {
        val article = Article("自定义 View 1-1 绘制基础", R.mipmap.hen_coder_draw1)
        list.add(article)

        val henCoderAdapter = HenCoderAdapter()
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = henCoderAdapter
        recyclerView.layoutManager = staggeredGridLayoutManager
    }

    private inner class HenCoderAdapter : RecyclerView.Adapter<HenCoderAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HenCoderAdapter.ViewHolder {
            val view = LayoutInflater.from(baseContext).inflate(R.layout.item_hen_coder_rv, null)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: HenCoderAdapter.ViewHolder, position: Int) {
            val item = list[position]
            holder.text.text = item.text
            holder.background.setImageResource(item.backgroundRes)
            holder.view.setOnClickListener {
                if (position ==0 ) {
                     val intent = Intent(baseContext, Draw1Activity::class.java)
                     startActivity(intent)
                }
            }
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val view: View = itemView.findViewById(R.id.item_view)
            val text: TextView = itemView.findViewById(R.id.tv_text)
            val background: ImageView = itemView.findViewById(R.id.iv_background)
        }

    }

    private data class Article(
        var text: String,
        var backgroundRes: Int
    )

}
