package paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * RadialGradient 辐射渐变
 *
 * @author Created by Lee64 on 2017/9/21.
 */

public class RadialGradientView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RadialGradientView(Context context) {
        super(context);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        // 用 Paint.setShader(shader) 设置一个 RadialGradient
        // RadialGradient 的参数：圆心坐标：(300, 300)；半径：200；颜色：#E91E63 到 #2196F3
        Shader shader = new RadialGradient(300, 300, 200, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint.setShader(shader);
        //  参数： centerX centerY：辐射中心的坐标
        // radius：辐射半径
        // centerColor：辐射中心的颜色
        // edgeColor：辐射边缘的颜色
        // tileMode：辐射范围之外的着色模式。
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
