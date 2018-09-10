package widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 支付宝 "咻咻咻" 式
 */
public class AliXiuXiuView extends View {

    private float mInitRadius;      // 初始波纹半径
    private float mMaxRadius;       // 最大波纹半径
    private float mMaxRadiusRate;

    private long mDuration = 2000;   // 一个波纹从创建到消失的持续时间
    private int mSpeed = 500;       // 波纹的创建速度，每500ms创建一个

    private Interpolator mInterpolator;
    private List<Circle> mCircleList = new ArrayList<>();

    private Paint mPaint;
    private boolean mIsRunning;
    private long mLastCreateTime;
    private boolean mMaxRadiusSet;

    private Runnable mCreateCircle = new Runnable() {

        @Override
        public void run() {
            if (mIsRunning) {
                newCircle();
                postDelayed(mCreateCircle, mSpeed);
            }
        }

    };

    public AliXiuXiuView(Context context) {
        super(context);
        init();
    }

    public AliXiuXiuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AliXiuXiuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AliXiuXiuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mInterpolator = new LinearInterpolator();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        if (mMaxRadiusSet) return;
        mMaxRadius = Math.min(w, h) * mMaxRadiusRate / 2.0f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator<Circle> iterator = mCircleList.iterator();
        boolean hasNext = iterator.hasNext();
        while (hasNext) {
            Circle circle = iterator.next();
            if (System.currentTimeMillis() - circle.createTime < mDuration) {
                int alpha = circle.getAlpha();
                mPaint.setAlpha(alpha);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, circle.getCurrentRadius(), mPaint);
            } else {
                iterator.remove();
            }
            hasNext = iterator.hasNext();
        }
        if (mCircleList.size() > 0) {
            postInvalidateDelayed(10);
        }
    }

    public void start() {
        if (mIsRunning) return;
        mIsRunning = true;
        mCreateCircle.run();
    }

    public void stop() {
        mIsRunning = false;
    }

    public void setStyle(Paint.Style style) {
        mPaint.setStyle(style);
    }

    public void setInitRadius(float radius) {
        this.mInitRadius = radius;
    }

    public void setDuration(long duration) {
        this.mDuration = duration;
    }

    public void setMaxRadius(float maxRadius) {
        this.mMaxRadius = maxRadius;
        mMaxRadiusSet = true;
    }

    public void setSpeed(int speed) {
        mSpeed = speed;
    }

    public void setMaxRadiusRate(float maxRadiusRate) {
        this.mMaxRadiusRate = maxRadiusRate;
    }

    public void setColor(int color) {
        mPaint.setColor(color);
    }

    private void newCircle() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastCreateTime < mSpeed) {
            return;
        }
        Circle circle = new Circle();
        mCircleList.add(circle);
        invalidate();
        mLastCreateTime = currentTime;
    }

    private class Circle {

        long createTime;

        Circle() {
            createTime = System.currentTimeMillis();
        }

        int getAlpha() {
            float percent = (System.currentTimeMillis() - createTime) * 1.0f / mDuration;
            float value = 1.0f - mInterpolator.getInterpolation(percent);
            return (int) (value * 255);
        }

        float getCurrentRadius() {
            float percent = (System.currentTimeMillis() - createTime) * 1.0f / mDuration;
            return mInitRadius + mInterpolator.getInterpolation(percent) * (mMaxRadius - mInitRadius);
        }

    }


}
