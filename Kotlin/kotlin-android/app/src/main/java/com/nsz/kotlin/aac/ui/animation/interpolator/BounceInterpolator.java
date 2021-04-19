package com.nsz.kotlin.aac.ui.animation.interpolator;

import android.view.animation.Interpolator;

// 反弹插补器
// https://evgenii.com/blog/spring-button-animation-on-android/
public class BounceInterpolator implements Interpolator {

    private double mAmplitude = 1;  // 反弹幅度。值越高 反弹越明显。
    private double mFrequency = 10; // 跳动的频率。较高的值会在动画时间段内产生更多的摆动。

    public BounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    @Override
    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time / mAmplitude) * Math.cos(mFrequency * time) + 1);
    }

}