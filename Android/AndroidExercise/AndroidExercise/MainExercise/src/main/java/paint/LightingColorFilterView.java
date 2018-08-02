package paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.nsz.android.R;

/**
 * 这个 LightingColorFilter 是用来模拟简单的光照效果的。
 *
 * @author Created by Lee64 on 2017/9/21.
 */

public class LightingColorFilterView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Bitmap bitmap;

    public LightingColorFilterView(Context context) {
        super(context);
    }

    public LightingColorFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LightingColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        // LightingColorFilter 的构造方法是 LightingColorFilter(int mul, int add) ，
        // 参数里的 mul 和 add 都是和颜色值格式相同的 int 值，其中 mul 用来和目标像素相乘，add 用来和目标像素相加：
        // R' = R * mul.R / 0xff + add.R
        // G' = G * mul.G / 0xff + add.G
        // B' = B * mul.B / 0xff + add.B

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        ColorFilter lightingColorFilter = new LightingColorFilter(0x00ffff, 0x000000);
        paint.setColorFilter(lightingColorFilter);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(40, 100);

        // 使用 Paint.setColorFilter() 来设置 LightingColorFilter

        // 第一个 LightingColorFilter：去掉红色部分
        canvas.drawBitmap(bitmap, 0, 0, paint);

        // 第二个 LightingColorFilter：增强绿色部分
        canvas.drawBitmap(bitmap, bitmap.getWidth() + 100, 0, paint);
    }

}
