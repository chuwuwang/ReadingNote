package com.cat.view;

import android.app.Application;
import android.content.Context;

import xcrash.XCrash;

public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        XCrash.init(this);
    }

}