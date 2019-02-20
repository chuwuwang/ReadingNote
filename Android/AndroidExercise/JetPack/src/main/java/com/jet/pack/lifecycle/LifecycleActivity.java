package com.jet.pack.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jet.pack.BaseJetPackActivity;
import com.jet.pack.R;

public class LifecycleActivity extends BaseJetPackActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lifecycle_activity_main);

        initToolbarBringBack("Lifecycle");

        Java8Observer observer = new Java8Observer();
        getLifecycle().addObserver(observer);
    }


}
