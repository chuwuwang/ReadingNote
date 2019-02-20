package paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.nsz.android.R;

/**
 * @author Created by Lee64 on 2017/9/21.
 */

public class XFerModeView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Bitmap bitmap1;
    private Bitmap bitmap2;

    public XFerModeView(Context context) {
        super(context);
    }

    public XFerModeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XFerModeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(50, 20);

        Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC);
        paint.setXfermode(xfermode);

        canvas.drawBitmap(bitmap1, 0, 0, paint);
        // 第一个：PorterDuff.Mode.SRC
        canvas.drawBitmap(bitmap2, 0, 0, paint);

        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap1, bitmap1.getWidth() + 100, 0, paint);
        // 第二个：PorterDuff.Mode.DST_IN
        canvas.drawBitmap(bitmap2, bitmap1.getWidth() + 100, 0, paint);

        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap1, 0, bitmap1.getHeight() + 20, paint);
        // 第三个：PorterDuff.Mode.DST_OUT
        canvas.drawBitmap(bitmap2, 0, bitmap1.getHeight() + 20, paint);

        // 用完之后使用 canvas.restore() 恢复 off-screen buffer
        paint.setXfermode(null);
    }

}
