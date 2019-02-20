package widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nsz.android.R;

import java.util.Random;

/**
 * 爱心点赞的贝塞尔曲线
 *
 * @author Lee64
 */
public class LoveBezierLayout extends RelativeLayout {

    private Drawable firstDrawable;
    private Drawable secondDrawable;
    private Drawable threeDrawable;
    private Drawable fourDrawable;
    private Drawable[] drawable;

    // 图片的宽高
    private int mDrawableWidth;
    private int mDrawableHeight;

    // 布局的宽高
    private int mWidth;
    private int mHeight;

    private Interpolator linear = new LinearInterpolator();
    private Interpolator accelerate = new AccelerateInterpolator();
    private Interpolator decelerate = new DecelerateInterpolator();
    private Interpolator accelerateDecelerate = new AccelerateDecelerateInterpolator();
    private Interpolator overshoot = new OvershootInterpolator();
    private Interpolator[] interpolator;

    private LayoutParams params;

    private Random mRandom;

    public LoveBezierLayout(Context context) {
        super(context);
        init();
    }

    public LoveBezierLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoveBezierLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressWarnings("deprecation")
    private void init() {
        drawable = new Drawable[4];
        firstDrawable = getResources().getDrawable(R.drawable.ic_photo_liked);
        secondDrawable = getResources().getDrawable(R.drawable.ic_my_likes);
        threeDrawable = getResources().getDrawable(R.drawable.ic_note_and_review_liked);
        fourDrawable = getResources().getDrawable(R.drawable.ic_photo_liked);
        drawable[0] = firstDrawable;
        drawable[1] = secondDrawable;
        drawable[2] = threeDrawable;
        drawable[3] = fourDrawable;
        // 获取图片的宽高
        mDrawableWidth = firstDrawable.getIntrinsicWidth() * 2;
        mDrawableHeight = firstDrawable.getIntrinsicHeight() * 2;

        params = new LayoutParams(mDrawableWidth, mDrawableHeight);
        // 给爱心控件动态布局,使得爱心始终在布局最底部的中间位置
        params.addRule(CENTER_HORIZONTAL, TRUE);
        params.addRule(ALIGN_PARENT_BOTTOM, TRUE);

        interpolator = new Interpolator[5];
        interpolator[0] = linear;
        interpolator[1] = accelerate;
        interpolator[2] = decelerate;
        interpolator[3] = accelerateDecelerate;
        interpolator[4] = overshoot;

        mRandom = new Random();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    /**
     * 添加爱心图片
     */
    public void addLoveDrawable() {
        final ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawable[mRandom.nextInt(4)]);
        imageView.setLayoutParams(params);
        addView(imageView);
        // 属性动画控制坐标
        AnimatorSet set = getAnimator(imageView);
        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 结束后,将ImageView移除
                removeView(imageView);
            }

        });
        set.start();
    }

    private AnimatorSet getAnimator(ImageView imageView) {
        // 透明度动画
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 0.3f, 1f);
        // 缩放动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 0.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 0.2f, 1f);

        AnimatorSet enterSet = new AnimatorSet();
        enterSet.setDuration(500);
        enterSet.playTogether(alpha, scaleX, scaleY);
        enterSet.setTarget(imageView);

        // 贝塞尔曲线动画(核心是不断修改ImageView的坐标)
        ValueAnimator valueAnimator = getBezierValueAnimator(imageView);
        AnimatorSet bezierSet = new AnimatorSet();
        // 先执行与后执行,按顺序执行
        bezierSet.playSequentially(enterSet, valueAnimator);
        bezierSet.setInterpolator(interpolator[mRandom.nextInt(5)]);
        bezierSet.setTarget(imageView);
        return bezierSet;
    }

    /**
     * 构造一个贝塞尔曲线动画
     */
    private ValueAnimator getBezierValueAnimator(final ImageView imageView) {
        PointF pointF0 = new PointF((mWidth - mDrawableWidth) / 2, mHeight - mDrawableHeight);
        PointF pointF1 = getPointF(1);
        PointF pointF2 = getPointF(2);
        PointF pointF3 = new PointF(mRandom.nextInt(mWidth), 0);

        // 通过插值器来控制View的运动路径
        LoveBezierEvaluator evaluator = new LoveBezierEvaluator(pointF1, pointF2);
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, pointF0, pointF3);
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                imageView.setX(pointF.x);
                imageView.setY(pointF.y);
                imageView.setAlpha(1 - animation.getAnimatedFraction());
            }

        });
        animator.setDuration(2500);
        animator.setTarget(imageView);
        return animator;
    }

    private PointF getPointF(int i) {
        PointF pointF = new PointF();
        pointF.x = mRandom.nextInt(mWidth);
        if (i == 2) {
            pointF.y = mRandom.nextInt(mWidth / 2);
        } else {
            pointF.y = mRandom.nextInt(mWidth / 2) + mWidth / 2;
        }
        return pointF;
    }

}
