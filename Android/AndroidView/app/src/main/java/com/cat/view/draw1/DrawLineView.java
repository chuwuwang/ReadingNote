package com.cat.view.draw1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawLineView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public DrawLineView(Context context) {
        super(context);
    }

    public DrawLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 练习内容：使用 canvas.drawLine() 方法画直线

        paint.setStrokeWidth(4);
        paint.setColor(Color.BLACK);
        canvas.drawLine(200, 200, 600, 400, paint);
    }

}