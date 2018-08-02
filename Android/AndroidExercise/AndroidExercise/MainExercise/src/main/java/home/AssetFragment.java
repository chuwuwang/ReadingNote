package home;

import com.nsz.android.R;

/**
 * @author Created by Lee64 on 2017/10/3.
 */

public class AssetFragment extends BaseFragment {

    public static AssetFragment newInstance() {
        AssetFragment fragment = new AssetFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_asset;
    }

}
