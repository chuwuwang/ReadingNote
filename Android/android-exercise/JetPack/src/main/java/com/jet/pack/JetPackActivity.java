package com.jet.pack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.base.core.BaseAppCompatActivity;
import com.jet.pack.data.binding.DataBindingActivity;
import com.jet.pack.lifecycle.LifecycleActivity;

public class JetPackActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initToolbarBringBack("JetPack");
    }

    private void initView() {
        findViewById(R.id.mb_lifecycle).setOnClickListener(this);
        findViewById(R.id.mb_data_binding).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.mb_lifecycle) {
            openActivity(LifecycleActivity.class);
        } else if (id == R.id.mb_data_binding) {
            openActivity(DataBindingActivity.class);
        }
    }


}
