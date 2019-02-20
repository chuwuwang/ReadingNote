package com.mvp;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author Created by Lee64 on 2018/3/25.
 */

public abstract class BasePresenter<T> {

    protected Reference<T> mViewReference; // View接口类型的弱引用

    public void attachView(T view) {
        mViewReference = new WeakReference<T>(view);
    }

    protected T getView() {
        return mViewReference.get();
    }

    public boolean isViewAttached() {
        return mViewReference != null && mViewReference.get() != null;
    }

    public void detachView() {
        if (mViewReference != null) {
            mViewReference.clear();
            mViewReference = null;
        }
    }

}
