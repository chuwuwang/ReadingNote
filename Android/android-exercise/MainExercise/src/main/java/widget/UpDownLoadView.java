package widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nsz.android.R;
import com.nsz.android.utils.DPUtil;

/**
 * @author Created by Lee64 on 2017/10/10.
 */

public class UpDownLoadView extends FrameLayout {

    private ImageView indication;
    private TextView loadTextView;
    private ShapeLoadView shapeLoadView;

    private String loadingText;

    public float factor = 1.2f;
    private float distance = 200;
    private static final int ANIMATION_DURATION = 500;

    private AnimatorSet downAnimator;
    private AnimatorSet upThrowAnimator;

    public UpDownLoadView(@NonNull Context context) {
        super(context);
    }

    public UpDownLoadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UpDownLoadView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UpDownLoadView);
        loadingText = typedArray.getString(R.styleable.UpDownLoadView_loadingText);
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = View.inflate(getContext(), R.layout.widget_layout_up_down_load, null);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        shapeLoadView = (ShapeLoadView) view.findViewById(R.id.shape_view);
        indication = (ImageView) view.findViewById(R.id.iv_indication);
        loadTextView = (TextView) view.findViewById(R.id.tv_load_text);
        setLoadingText(loadingText);
        addView(view, layoutParams);
        distance = DPUtil.dip2px(getContext(), 54f);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopLoading();
    }

    private Runnable freeFallRunnable = new Runnable() {

        @Override
        public void run() {
            freeFall();
        }

    };

    public void setLoadingText(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            loadTextView.setVisibility(GONE);
        } else {
            loadTextView.setVisibility(VISIBLE);
        }
        loadTextView.setText(text);
    }

    public void startLoading() {
        startLoading(0);
    }

    public void startLoading(long delay) {
        if (downAnimator != null && downAnimator.isRunning()) {
            return;
        }
        this.removeCallbacks(freeFallRunnable);
        if (delay > 0) {
            this.postDelayed(freeFallRunnable, delay);
        } else {
            this.post(freeFallRunnable);
        }
    }

    public void stopLoading() {
        if (upThrowAnimator != null) {
            if (upThrowAnimator.isRunning()) {
                upThrowAnimator.cancel();
            }
            upThrowAnimator.removeAllListeners();
            for (Animator animator : upThrowAnimator.getChildAnimations()) {
                animator.removeAllListeners();
            }
        }
        if (downAnimator != null) {
            if (downAnimator.isRunning()) {
                downAnimator.cancel();
            }
            downAnimator.removeAllListeners();
            for (Animator animator : downAnimator.getChildAnimations()) {
                animator.removeAllListeners();
            }
        }
        removeCallbacks(freeFallRunnable);
    }

    /**
     * 上抛
     */
    private void upThrow() {
        ObjectAnimator translationY = ObjectAnimator.ofFloat(shapeLoadView, "translationY", distance, 0);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(indication, "scaleX", 0.2f, 1);
        ObjectAnimator animator = null;
        switch (shapeLoadView.getShape()) {
            case TRIANGLE:
                animator = ObjectAnimator.ofFloat(shapeLoadView, "rotation", 0, -120);
                break;
            case CIRCLE:
                animator = ObjectAnimator.ofFloat(shapeLoadView, "rotation", 0, 180);
                break;
            case RECT:
                animator = ObjectAnimator.ofFloat(shapeLoadView, "rotation", 0, 180);
                break;
        }
        translationY.setDuration(ANIMATION_DURATION);
        translationY.setInterpolator(new DecelerateInterpolator(factor));
        animator.setDuration(ANIMATION_DURATION);
        animator.setInterpolator(new DecelerateInterpolator(factor));
        upThrowAnimator = new AnimatorSet();
        upThrowAnimator.setDuration(ANIMATION_DURATION);
        upThrowAnimator.playTogether(translationY, animator, scaleX);
        upThrowAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                freeFall();
            }

        });
        upThrowAnimator.start();
    }

    /**
     * 下落
     */
    private void freeFall() {
        ObjectAnimator translationY = ObjectAnimator.ofFloat(shapeLoadView, "translationY", 0, distance);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(indication, "scaleX", 1, 0.2f);
        translationY.setDuration(ANIMATION_DURATION);
        translationY.setInterpolator(new AccelerateInterpolator(factor));
        downAnimator = new AnimatorSet();
        downAnimator.setDuration(ANIMATION_DURATION);
        downAnimator.playTogether(translationY, scaleX);
        downAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                shapeLoadView.changeShape();
                upThrow();
            }

        });
        downAnimator.start();
    }

}
