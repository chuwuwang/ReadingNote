package paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 使用 PathEffect 来给图形的轮廓设置效果。对 Canvas 所有的图形绘制有效，也就是 drawLine() drawCircle() drawPath() 这些方法。
 *
 * @author Created by Lee64 on 2017/9/21.
 */

public class PathEffectView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path path = new Path();


    public PathEffectView(Context context) {
        super(context);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect
        // 把所有拐角变成圆角。
        PathEffect effect = new CornerPathEffect(20);
        paint.setPathEffect(effect);
        canvas.drawPath(path, paint);

        // 第二处：DiscretePathEffect
        // 把线条进行随机的偏离，让轮廓变得乱七八糟。乱七八糟的方式和程度由参数决定。
        canvas.save();
        canvas.translate(500, 0);
        effect = new DiscretePathEffect(10, 5);
        paint.setPathEffect(effect);
        canvas.drawPath(path, paint);
        canvas.restore();

        // 第三处：DashPathEffect
        // 使用虚线来绘制线条。
        canvas.save();
        canvas.translate(0, 200);
        effect = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
        paint.setPathEffect(effect);
        canvas.drawPath(path, paint);
        canvas.restore();

        // PathDashPathEffect这个方法比 DashPathEffect 多一个前缀 Path ，所以顾名思义，它是使用一个 Path 来绘制「虚线」。
        canvas.save();
        canvas.translate(500, 200);
        // 第四处：PathDashPathEffect
        // 使用一个三角形来做 dash
        Path dashPath = new Path();
        dashPath.rLineTo(10, 20);
        dashPath.rLineTo(30, 50);
        dashPath.rLineTo(10, 60);
        effect = new PathDashPathEffect(dashPath, 40, 0, PathDashPathEffect.Style.TRANSLATE);
        paint.setPathEffect(effect);
        canvas.drawPath(path, paint);
        canvas.restore();

        // 第五处：SumPathEffect
        // SumPathEffect 这是一个组合效果类的 PathEffect 。它的行为特别简单，就是分别按照两种 PathEffect 分别对目标进行绘制。
        canvas.save();
        canvas.translate(0, 400);
        PathEffect dashEffect = new DashPathEffect(new float[]{20, 10}, 0);
        PathEffect discreteEffect = new DiscretePathEffect(20, 5);
        effect = new SumPathEffect(dashEffect, discreteEffect);
        paint.setPathEffect(effect);
        canvas.drawPath(path, paint);
        canvas.restore();

        // 第六处：ComposePathEffect
        // 这也是一个组合效果类的 PathEffect 。不过它是先对目标 Path 使用一个 PathEffect，然后再对这个改变后的 Path 使用另一个 PathEffect。
        canvas.save();
        canvas.translate(500, 400);
        PathEffect dashEff = new DashPathEffect(new float[]{20, 10}, 0);
        PathEffect discreteEff = new DiscretePathEffect(20, 5);
        effect = new SumPathEffect(dashEff, discreteEff);
        paint.setPathEffect(effect);
        canvas.drawPath(path, paint);
        canvas.restore();
    }

}
