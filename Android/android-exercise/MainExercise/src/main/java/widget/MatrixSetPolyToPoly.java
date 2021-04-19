package widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import com.nsz.android.R;

/**
 * setPolyToPoly最多可以支持4个点，这四个点通常为图形的四个角，可以通过这四个角将视图从矩形变换成其他形状。
 *
 * @author Lee64
 */
public class MatrixSetPolyToPoly extends View {

    private Bitmap bitmap;
    // 测试setPolyToPoly用的Matrix
    private Matrix polyMatrix;

    public MatrixSetPolyToPoly(Context context) {
        super(context);
        initBitmapAndMatrix();
    }

    public MatrixSetPolyToPoly(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBitmapAndMatrix();
    }

    public MatrixSetPolyToPoly(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBitmapAndMatrix();
    }

    private void initBitmapAndMatrix() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.quadratic_element);
        polyMatrix = new Matrix();
        float[] src = {
                0, 0, bitmap.getWidth(),
                0, bitmap.getWidth(), bitmap.getHeight(),
                0, bitmap.getHeight()
        };
        float[] dst = {
                0, 0, bitmap.getWidth(),
                400, bitmap.getWidth(), bitmap.getHeight() - 200,
                0, bitmap.getHeight()
        };

        // src.length >> 1 为位移运算 相当于除以2
        polyMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);

        // 此处为了更好的显示对图片进行了等比缩放和平移(图片本身有点大)
        polyMatrix.preScale(0.2f, 0.2f);
        polyMatrix.preTranslate(100, 50);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 根据Matrix绘制一个变换后的图片
        canvas.drawBitmap(bitmap, polyMatrix, null);
    }

}
