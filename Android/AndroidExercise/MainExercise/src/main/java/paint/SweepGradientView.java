package paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * SweepGradient 扫描渐变
 *
 * @author Created by Lee64 on 2017/9/21.
 */

public class SweepGradientView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public SweepGradientView(Context context) {
        super(context);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        // 用 Paint.setShader(shader) 设置一个 RadialGradient
        // RadialGradient 的参数：圆心坐标：(300, 300)；半径：200；颜色：#E91E63 到 #2196F3

        // 构造方法： SweepGradient(float cx, float cy, int color0, int color1)
        // 参数： cx cy ：扫描的中心
        // color0：扫描的起始颜色
        // color1：扫描的终止颜色
        Shader shader = new SweepGradient(300, 300, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"));
        paint.setShader(shader);
    }

    private int mWidth, mHeight;

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 4, mHeight / 4 - 100);

        canvas.drawCircle(200, 200, 200, paint);
    }

}
