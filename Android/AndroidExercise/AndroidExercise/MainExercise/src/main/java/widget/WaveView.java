package widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

/**
 * 贝塞尔波浪效果
 *
 * @author Lee64
 */
public class WaveView extends View {

    private Path mPath;
    private Paint mPaint;

    private float width;
    private float height;

    private boolean hasInit;
    private boolean hasRunning;

    // 波峰
    private float wavePeak = 50f;
    // 波槽
    private float waveTrough = 50f;
    // 水位
    private float waveHeight = 250f;

    private PointF startPoint;
    private PointF endPoint;

    private PointF leftPoint;
    private PointF centerPoint;
    private PointF rightPoint;

    private PointF leftFirstControlPoint;
    private PointF leftSecondControlPoint;
    private PointF rightFirstControlPoint;
    private PointF rightSecondControlPoint;

    private static final int EXE_WAVE_ANIMATION = 0x55;
    private WaveHandler waveHandler;

    private ValueAnimator animator;

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#3949ab"));

        waveHandler = new WaveHandler();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        if (!hasInit) {
            width = w;
            height = h;
            hasInit = true;
            Log.d("nsz", "w : " + w + " h : " + h);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (hasRunning && hasInit) {
            mPath.reset();

            mPath.moveTo(startPoint.x, startPoint.y);
            mPath.quadTo(leftFirstControlPoint.x, leftFirstControlPoint.y, leftPoint.x, leftPoint.y);
            mPath.quadTo(leftSecondControlPoint.x, leftSecondControlPoint.y, centerPoint.x, centerPoint.y);
            mPath.quadTo(rightFirstControlPoint.x, rightFirstControlPoint.y, rightPoint.x, rightPoint.y);
            mPath.quadTo(rightSecondControlPoint.x, rightSecondControlPoint.y, endPoint.x, endPoint.y);

            mPath.lineTo(endPoint.x, height);
            mPath.lineTo(startPoint.x, height);

            canvas.drawPath(mPath, mPaint);
        }
    }

    public void setWaveHeight(int h) {
        waveHeight = h;
        if (waveHandler != null) {
            waveHandler.removeCallbacksAndMessages(null);
        }
        exeWaveEffect();
    }

    public void exeWaveEffect() {
        if (waveHandler != null && waveHandler.hasMessages(EXE_WAVE_ANIMATION)) {
            return;
        }
        if (animator != null) {
            animator.removeAllUpdateListeners();
            animator.cancel();
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (WaveView.this) {
                    while (!hasInit) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    waveHandler.sendEmptyMessage(EXE_WAVE_ANIMATION);
                }
            }

        }).start();
    }

    @SuppressLint("HandlerLeak")
    private class WaveHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == EXE_WAVE_ANIMATION) {
                reset();
                hasRunning = true;
                startWaveAnimation();
            }
        }

    }

    private void startWaveAnimation() {
        animator = ValueAnimator.ofFloat(startPoint.x, 0);
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(Animation.INFINITE);
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startPoint.x = (float) animation.getAnimatedValue();
                // waveHeight += 1;

                float y = height - waveHeight;
                leftPoint = new PointF(startPoint.x + width / 2, y);
                centerPoint = new PointF(leftPoint.x + width / 2, y);
                rightPoint = new PointF(centerPoint.x + width / 2, y);
                endPoint = new PointF(rightPoint.x + width / 2, y);

                leftFirstControlPoint = new PointF(startPoint.x + width / 4, startPoint.y + wavePeak);
                leftSecondControlPoint = new PointF(leftPoint.x + width / 4, leftPoint.y - waveTrough);
                rightFirstControlPoint = new PointF(centerPoint.x + width / 4, centerPoint.y + wavePeak);
                rightSecondControlPoint = new PointF(rightPoint.x + width / 4, rightPoint.y - waveTrough);

                invalidate();
            }

        });
        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                reset();
            }

        });
        animator.start();
    }

    private void reset() {
        float y = height - waveHeight;
        startPoint = new PointF(-width, y);
        endPoint = new PointF(width, y);

        leftPoint = new PointF(-width / 2, y);
        centerPoint = new PointF(0, y);
        rightPoint = new PointF(width / 2, y);

        leftFirstControlPoint = new PointF(startPoint.x + width / 4, startPoint.y + wavePeak);
        leftSecondControlPoint = new PointF(leftPoint.x + width / 4, leftPoint.y - waveTrough);
        rightFirstControlPoint = new PointF(centerPoint.x + width / 4, centerPoint.y + wavePeak);
        rightSecondControlPoint = new PointF(rightPoint.x + width / 4, rightPoint.y - waveTrough);
    }

}
