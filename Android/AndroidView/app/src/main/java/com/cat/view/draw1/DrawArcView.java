package com.cat.view.draw1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawArcView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public DrawArcView(Context context) {
        super(context);
    }

    public DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 练习内容：使用 canvas.drawArc() 方法画弧形和扇形

        paint.setColor(Color.BLACK);
        canvas.drawArc(100, 100, 600, 500, -110, 80, true, paint);

        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(100, 100, 600, 500, -170, 40, false, paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(100, 100, 600, 600, 0, 180, true, paint);
    }

}