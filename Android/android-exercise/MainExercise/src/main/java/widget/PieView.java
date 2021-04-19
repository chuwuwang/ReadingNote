package widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * 饼状图
 *
 * @author lee.shenzhou
 */
public class PieView extends View {

    // 颜色表
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000,
            0xFF808000, 0xFFFF8C69, 0xFF808080, 0xFFE6B800, 0xFF7CFC00};

    private Paint mPaint;

    // 饼状图的初始绘制角度
    private float mStartAngle;

    // 绘制时控制文本绘制的范围
    private Rect mBound;

    private int mWidth;
    private int mHeight;

    private List<Pie> mData;

    public PieView(Context context) {
        super(context);
        init();
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Style.FILL);
        mPaint.setAntiAlias(true);
    }

    /**
     * 设置起始角度
     */
    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();
    }

    public void setData(List<Pie> data) {
        if (data == null || data.isEmpty()) {
            return;
        }

        mData = data;

        int size = mData.size();

        float sumValue = 0;
        for (int i = 0; i < size; i++) {
            Pie pie = mData.get(i);
            // 计算数值和
            sumValue += pie.value;

            // 设置颜色
            if (pie.color == 0) {
                int j = i % mColors.length;
                pie.color = mColors[j];
            }
        }

        for (int i = 0; i < size; i++) {
            Pie pie = mData.get(i);
            // 计算百分比
            float precentage = pie.value / sumValue;
            // 计算角度
            float angle = precentage * 360;

            pie.percentage = precentage;
            pie.angle = angle;
        }

        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData == null || mData.isEmpty()) {
            return;
        }

        float currentStartAngle = mStartAngle;
        canvas.translate(mWidth / 2, mHeight / 2);
        // 饼状图半径
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rect = new RectF(-r, -r, r, r);

        int size = mData.size();
        for (int i = 0; i < size; i++) {
            Pie pie = mData.get(i);
            mPaint.setColor(pie.color);
            canvas.drawArc(rect, currentStartAngle, pie.angle, true, mPaint);

            // 计算弧长
            int l = (int) (pie.angle * Math.PI / 180 * r);
            Log.i("nsz", "弧长：" + l);

            // 计算角度
            float angle = pie.angle / 2 + currentStartAngle;

            // 转化为弧度
            angle = (float) (angle * Math.PI / 180);

            float x = (float) (r * Math.cos(angle));
            float y = (float) (r * Math.sin(angle));

            float dx = (float) (x * 0.2 + x);
            float dy = (float) (y + 0.2 * y);

            mPaint.setColor(Color.WHITE);

            // 画线
            canvas.drawLine(0, 0, dx, dy, mPaint);

            mBound = new Rect();
            String text = pie.name + " " + (int) (pie.percentage * 100) + "%";
            mPaint.getTextBounds(text, 0, text.length(), mBound);

            // 画百分比
            Path path = new Path();
            path.moveTo(x / 2 - mBound.width() / 2, y / 2 + mBound.height() / 2);
            path.lineTo(x / 2 + mBound.width() / 2, y / 2 + mBound.height() / 2);
            canvas.drawTextOnPath(text, path, 0, 0, mPaint);

            currentStartAngle += pie.angle;
        }
    }

    /**
     * 每个饼状图的数据
     */
    public static class Pie {

        public String name;      // 名称
        public float value;      // 数值
        public int color;          // 颜色
        private float angle;      // 角度
        private float percentage; // 百分比

        public Pie(String name, float value) {
            super();
            this.name = name;
            this.value = value;
        }

        public Pie(String name, float value, int color) {
            super();
            this.name = name;
            this.value = value;
            this.color = color;
        }

        public Pie() {
            super();
        }

    }

}
