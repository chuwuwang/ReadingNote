package paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.nsz.android.R;

/**
 * 这个就厉害了。ColorMatrixColorFilter 使用一个 ColorMatrix 来对颜色进行处理。 ColorMatrix 这个类，内部是一个 4x5 的矩阵：
 *
 * @author Created by Lee64 on 2017/9/21.
 */

public class ColorMatrixColorFilterView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Bitmap bitmap;

    public ColorMatrixColorFilterView(Context context) {
        super(context);
    }

    public ColorMatrixColorFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorMatrixColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        // 使用 setColorFilter() 设置一个 ColorMatrixColorFilter
        // 用 ColorMatrixColorFilter.setSaturation() 把饱和度去掉
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        float[] src = new float[]{
                1, 0, 0, 0, 1,
                0, 1, 1, 0, 0,
                1, 0, 1, 1, 0,
                0, 1, 0, 1, 1
        };
        ColorFilter colorFilter = new ColorMatrixColorFilter(src);
        paint.setColorFilter(colorFilter);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(100, 100);

        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

}
