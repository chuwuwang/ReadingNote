package paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.nsz.android.R;

/**
 * 用 Bitmap 来着色（终于不是渐变了）。其实也就是用 Bitmap 的像素来作为图形或文字的填充。
 *
 * @author Created by Lee64 on 2017/9/21.
 */

public class BitmapShaderView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public BitmapShaderView(Context context) {
        super(context);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        // 用 Paint.setShader(shader) 设置一个 BitmapShader
        // Bitmap: R.drawable.batman
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.matrix_camera);
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        // 参数： bitmap：用来做模板的 Bitmap 对象
        // tileX：横向的 TileMode
        // tileY：纵向的 TileMode。
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
