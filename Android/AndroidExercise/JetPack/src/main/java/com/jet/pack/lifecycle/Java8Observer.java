package com.jet.pack.lifecycle;

import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jet.pack.JetPack;

public class Java8Observer implements DefaultLifecycleObserver {

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Log.e(JetPack.TAG, "onCreate");
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        Log.e(JetPack.TAG, "onStart");
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        Log.e(JetPack.TAG, "onResume");
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        Log.e(JetPack.TAG, "onPause");
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        Log.e(JetPack.TAG, "onStop");
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        Log.e(JetPack.TAG, "onDestroy");
    }

}
