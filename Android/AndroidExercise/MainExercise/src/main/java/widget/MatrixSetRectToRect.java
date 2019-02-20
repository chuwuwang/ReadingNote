package widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.nsz.android.R;

public class MatrixSetRectToRect extends View {

    private int width;
    private int height;

    private Bitmap bitmap;
    // 测试etRectToRect用的Matrix
    private Matrix rectMatrix;

    public MatrixSetRectToRect(Context context) {
        super(context);
        init();
    }

    public MatrixSetRectToRect(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MatrixSetRectToRect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.quadratic_element);
        rectMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        width = w;
        height = h;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF src = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF dst = new RectF(0, 0, width, height);
        rectMatrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);
        canvas.drawBitmap(bitmap, rectMatrix, new Paint());
    }

}
