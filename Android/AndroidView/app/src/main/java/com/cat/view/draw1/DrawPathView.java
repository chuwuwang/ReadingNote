package com.cat.view.draw1;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawPathView extends View {

    public DrawPathView(Context context) {
        super(context);
    }

    public DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 练习内容：使用 canvas.drawPath() 方法画心形
    }

}