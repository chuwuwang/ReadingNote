package paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.nsz.android.R;

/**
 * ComposeShader 混合着色器
 *
 * @author Created by Lee64 on 2017/9/21.
 */

public class ComposeShaderView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ComposeShaderView(Context context) {
        super(context);
    }

    public ComposeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ComposeShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        // 硬件加速下 ComposeShader 不能使用两个同类型的 Shader
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        // 用 Paint.setShader(shader) 设置一个 ComposeShader
        // Shader 1: BitmapShader 图片：R.drawable.batman
        // Shader 2: BitmapShader 图片：R.drawable.batman_logo

        // 第一个 Shader：头像的 Bitmap
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);

        // 第二个 Shader：从上到下的线性渐变（由透明到黑色）
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        // ComposeShader：结合两个 Shader
        Shader shader = new ComposeShader(shader1, shader2, PorterDuff.Mode.DST_OUT);
        paint.setShader(shader);

        // 参数： shaderA, shaderB：两个相继使用的
        // Shader mode: 两个 Shader 的叠加模式，即 shaderA 和 shaderB 应该怎样共同绘制。它的类型是 PorterDuff.Mode 。

        // mode 改为 PorterDuff.Mode.DST_OUT，就会变成挖空效果：
        // 而如果再把 mode 改为 PorterDuff.Mode.DST_IN，就会变成蒙版抠图效果：
        // Mode: SRC_OVER。它的算法非常直观：就像上面图中的那样，把源图像直接铺在目标图像上。
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(100, 50, 600, 400, paint);
    }

}
