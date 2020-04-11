package com.cat.view.draw1

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.cat.view.R
import com.google.android.material.tabs.TabLayout

class Draw1Activity : AppCompatActivity() {

    private val list = mutableListOf<PageModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw1)
        initView()
    }

    private fun initView() {
        var pageModel = PageModel("drawCircle", R.layout.draw1_circle)
        list.add(pageModel)
        pageModel = PageModel("drawRect", R.layout.draw1_rect)
        list.add(pageModel)
        pageModel = PageModel("drawPoint", R.layout.draw1_point)
        list.add(pageModel)
        pageModel = PageModel("drawOval", R.layout.draw1_oval)
        list.add(pageModel)
        pageModel = PageModel("drawLine", R.layout.draw1_line)
        list.add(pageModel)
        pageModel = PageModel("drawRoundRect", R.layout.draw1_round_rect)
        list.add(pageModel)
        pageModel = PageModel("drawRoundArc", R.layout.draw1_arc)
        list.add(pageModel)
        pageModel = PageModel("drawRoundPath", R.layout.draw1_path)
        list.add(pageModel)
        pageModel = PageModel("直方图", R.layout.draw1_histogram)
        list.add(pageModel)
        pageModel = PageModel("饼图", R.layout.draw1_pie_chart)
        list.add(pageModel)

        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val pagerAdapter = Draw1PageAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
    }

    private inner class Draw1PageAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            val pageModel = list[position]
            return Draw1Fragment.newInstance(pageModel.layoutRes)
        }

        override fun getCount(): Int = list.size

        override fun getPageTitle(position: Int): CharSequence? {
            return list[position].titleRes
        }

    }

    private data class PageModel(
        var titleRes: String,
        @LayoutRes var layoutRes: Int
    )

}