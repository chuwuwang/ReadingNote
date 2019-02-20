package widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 蜘蛛网图
 *
 * @author Lee64
 */
public class RadarView extends View {

    // 数据个数
    private int count = 6;
    // 网格最大半径
    private float radius;
    // 每个角度
    private float angle = (float) (Math.PI * 2 / count);
    // 中心X
    private int centerX;
    // 中心Y
    private int centerY;

    // 数据最大值
    private float maxValue = 100;
    private String[] titles = {"输出", "防守", "突袭", "魔法师", "鬼剑士", "天启者"};
    // 各维度分值
    private double[] data = {100, 60, 60, 60, 100, 50, 10, 20};

    // 雷达区画笔
    private Paint mainPaint;
    // 数据区画笔
    private Paint valuePaint;
    // 文本画笔
    private Paint textPaint;

    public RadarView(Context context) {
        super(context);
        init();
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.GRAY);
        mainPaint.setStyle(Paint.Style.STROKE);

        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new Paint();
        textPaint.setTextSize(24);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
    }

    // 设置标题
    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    // 设置数值
    public void setData(double[] data) {
        this.data = data;
    }

    public float getMaxValue() {
        return maxValue;
    }

    // 设置最大数值
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    // 设置蜘蛛网颜色
    public void setMainPaintColor(int color) {
        mainPaint.setColor(color);
    }

    // 设置标题颜色
    public void setTextPaintColor(int color) {
        textPaint.setColor(color);
    }

    // 设置覆盖局域颜色
    public void setValuePaintColor(int color) {
        valuePaint.setColor(color);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        radius = Math.min(h, w) / 2 * 0.8f;
        centerX = w / 2;
        centerY = h / 2;

        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }

    /**
     * 绘制正多边形
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        // r是蜘蛛丝之间的间距
        float r = radius / (count - 1);

        for (int i = 0; i < count; i++) {
            // 当前半径
            float currR = r * i;
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + currR, centerY);
                } else {
                    // 根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerX + currR * Math.cos(angle * j));
                    float y = (float) (centerY + currR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();// 闭合路径
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制直线
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制文字
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < count; i++) {
            float x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(angle * i));
            float y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(angle * i));
            if (angle * i >= 0 && angle * i <= Math.PI / 2) {
                // 第1象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i >= 3 * Math.PI / 2 && angle * i <= Math.PI * 2) {
                // 第4象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i > Math.PI / 2 && angle * i <= Math.PI) {
                // 第2象限
                // 文本长度
                float dis = textPaint.measureText(titles[i]);
                canvas.drawText(titles[i], x - dis, y, textPaint);
            } else if (angle * i >= Math.PI && angle * i < 3 * Math.PI / 2) {
                // 第3象限
                float dis = textPaint.measureText(titles[i]);
                // 文本长度
                canvas.drawText(titles[i], x - dis, y, textPaint);
            }
        }
    }

    /**
     * 绘制区域
     */
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(255);
        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius * Math.cos(angle * i) * percent);
            float y = (float) (centerY + radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                path.moveTo(x, centerY);
            } else {
                path.lineTo(x, y);
            }
            // 绘制小圆点
            canvas.drawCircle(x, y, 10, valuePaint);
        }
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        // 绘制填充区域
        valuePaint.setAlpha(127);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }

}
