package com.nsz.android.home;

import com.nsz.android.R;

public class NewsFragment extends BaseFragment {

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    protected int getLayoutId() {
        return R.layout.main_fragment_news;
    }


}
