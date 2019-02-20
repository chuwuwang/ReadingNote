package widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.nsz.android.R;

public class ArrowPathMeasureView extends View {

    // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度
    private float currentValue = 0;
    // 当前点的实际位置
    private float[] pos;
    // 当前点的tangent值,用于计算图片所需旋转的角度
    private float[] tan;
    // 箭头图片
    private Bitmap bitmap;
    // 矩阵,用于对图片进行一些操作
    private Matrix matrix;

    private int mViewWidth;
    private int mViewHeight;

    private Paint defaultPaint;

    public ArrowPathMeasureView(Context context) {
        super(context);
        init();
    }

    public ArrowPathMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ArrowPathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        pos = new float[2];
        tan = new float[2];

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arrow, options);
        matrix = new Matrix();

        defaultPaint = new Paint();
        defaultPaint.setStrokeWidth(5);
        defaultPaint.setStyle(Style.STROKE);
        defaultPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        mViewWidth = w / 2;
        mViewHeight = h / 2;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mViewWidth, mViewHeight);

        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);
        PathMeasure measure = new PathMeasure(path, false);

        // 计算当前的位置在总长度上的比例[0,1]
        currentValue += 0.005;
        if (currentValue >= 1) {
            currentValue = 0;
        }

        // 获取当前位置的坐标以及趋势
        measure.getPosTan(measure.getLength() * currentValue, pos, tan);

        matrix.reset();

        // 计算图片旋转角度
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);

        // 旋转图片
        matrix.postRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        // 将图片绘制中心调整到与当前点重合
        matrix.postTranslate(pos[0] - bitmap.getWidth() / 2, pos[1] - bitmap.getHeight() / 2);

        canvas.drawPath(path, defaultPaint);
        canvas.drawBitmap(bitmap, matrix, defaultPaint);

        invalidate(); // 重绘页面
    }

}
