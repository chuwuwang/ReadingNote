package home;


import com.nsz.android.R;

/**
 * 资讯
 *
 * @author Created by Lee64 on 2017/10/3.
 */

public class NewsFragment extends BaseFragment {

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    protected int getLayoutId() {
        return R.layout.main_fragment_news;
    }


}
