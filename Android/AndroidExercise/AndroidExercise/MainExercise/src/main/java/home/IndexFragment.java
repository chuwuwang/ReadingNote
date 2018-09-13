package home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nsz.android.R;

import utils.FileHelperUtil;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FileHelperUtil.getCacheDir(mContext);
    }


}
