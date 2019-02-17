package com.jet.pack;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jet.pack.lifecycle.LifecycleActivity;

public class JetPackActivity extends BaseJetPackActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initToolbarBringBack("JetPack");
    }

    private void initView() {
        findViewById(R.id.mb_lifecycle).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.mb_lifecycle) {
            openActivity(LifecycleActivity.class);
        }
    }


}
