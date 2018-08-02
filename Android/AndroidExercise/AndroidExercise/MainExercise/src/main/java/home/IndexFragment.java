package home;

import com.nsz.android.R;

/**
 * 首页
 *
 * @author Created by Lee64 on 2017/10/3.
 */

public class IndexFragment extends BaseFragment {

    public static IndexFragment newInstance() {
        IndexFragment fragment = new IndexFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_index;
    }


}
