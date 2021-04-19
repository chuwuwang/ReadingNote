package com.cat.view.draw1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawRoundRectView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public DrawRoundRectView(Context context) {
        super(context);
    }

    public DrawRoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawRoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 练习内容：使用 canvas.drawRoundRect() 方法画圆角矩形

        paint.setColor(Color.BLACK);
        canvas.drawRoundRect(200, 200, 600, 400, 40, 40, paint);
    }

}
