package paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.nsz.android.R;


/**
 * 上一个方法 setShadowLayer() 是设置的在绘制层下方的附加效果；而这个 MaskFilter 和它相反，设置的是在绘制层上方的附加效果。
 */
public class MaskFilterView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Bitmap bitmap;

    public MaskFilterView(Context context) {
        super(context);
    }

    public MaskFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.what_the_fuck);
        // 到现在已经有两个 setXxxFilter(filter) 了。
        // 前面有一个 setColorFilter(filter)，是对每个像素的颜色进行过滤；
        // 而这里的 setMaskFilter(filter) 则是基于整个画面来进行过滤。
        // MaskFilter 有两种： BlurMaskFilter 和 EmbossMaskFilter。

        // 模糊效果的 MaskFilter。
        // 它的构造方法 BlurMaskFilter(float radius, BlurMaskFilter.Blur style) 中，
        // radius 参数是模糊的范围， style 是模糊的类型。一共有四种：
        // NORMAL: 内外都模糊绘制
        // SOLID: 内部正常绘制，外部模糊
        // INNER: 内部模糊，外部不绘制
        // OUTER: 内部不绘制，外部模糊（什么鬼？）

        // EmbossMaskFilter 浮雕效果的 MaskFilter。
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 用 Paint.setMaskFilter 来设置不同的 BlurMaskFilter

        // 第一个：NORMAL
        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
        canvas.drawBitmap(bitmap, 100, 50, paint);

        // 第二个：INNER
        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER));
        canvas.drawBitmap(bitmap, bitmap.getWidth() + 200, 50, paint);

        // 第三个：OUTER
        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.OUTER));
        canvas.drawBitmap(bitmap, 100, bitmap.getHeight() + 100, paint);

        // 第四个：SOLID
        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID));
        canvas.drawBitmap(bitmap, bitmap.getWidth() + 200, bitmap.getHeight() + 100, paint);
    }

}
