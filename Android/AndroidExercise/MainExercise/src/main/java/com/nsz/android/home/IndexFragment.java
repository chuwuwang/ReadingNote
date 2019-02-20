package com.nsz.android.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.nsz.android.R;
import com.nsz.android.utils.FileHelperUtil;
import com.tencent.mmkv.MMKV;

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
        init();
    }

    private void init() {
        String rootDir = MMKV.initialize(mContext);
        Log.e(Constant.TAG, "rootDir:" + rootDir);

        MMKV kv = MMKV.defaultMMKV();
        kv.encode("bool", true);
        boolean bool = kv.decodeBool("bool");
        Log.e(Constant.TAG, "bool:" + bool);

    }


}
