package com.sea.common.http.interceptor.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.sea.common.R
import com.sea.common.http.interceptor.HttpEntity
import com.sea.common.ui.activity.ImmersiveStatusBarActivity
import kotlinx.android.synthetic.main.http_activity_logs_detail.*
import org.jetbrains.anko.startActivity

class HttpLogsDetailActivity : ImmersiveStatusBarActivity() {

    private val entry: HttpEntity by lazy {
        intent.getSerializableExtra("extra_entry") as HttpEntity
    }

    private val tabLayoutMediator by lazy {
        TabLayoutMediator(tab_layout, view_pager2) { tab, position ->
            when (position) {
                0 -> tab.text = "OVERVIEW"
                1 -> tab.text = "REQUEST"
                else -> tab.text = "RESPONSE"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.http_activity_logs_detail)
        initView()
    }

    private fun initView() {
        http_detail_title.text = entry.requestHost

        view_pager2.adapter = fragmentStateAdapter
        view_pager2.registerOnPageChangeCallback(onPageChangeCallback)

        tabLayoutMediator.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        view_pager2.unregisterOnPageChangeCallback(onPageChangeCallback)
        tabLayoutMediator.detach()
    }

    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
        }

    }

    private val fragmentStateAdapter = object : FragmentStateAdapter(this) {

        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> HttpLogsOverviewFragment.create(entry)
                1 -> HttpLogsRequestFragment.create(entry)
                else -> HttpLogsResponseFragment.create(entry)
            }
        }

    }

    companion object {

        fun startActivity(context: Context, entry: HttpEntity) {
            context.startActivity<HttpLogsDetailActivity>(
                "extra_entry" to entry
            )
        }

    }

}