package transition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;

/**
 * 自定义的CircularReveal
 */
public class CircularRevealLayout extends FrameLayout {

    private static final int DEFAULT_DURATION = 600;

    private Path clipPath;
    private float clipRadius;
    private int clipCenterX;
    private int clipCenterY;

    private boolean isContentShown = true;

    private Animation animation;

    public CircularRevealLayout(Context context) {
        this(context, null);
    }

    public CircularRevealLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // sdk版本在11-18之间
    public CircularRevealLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        clipPath = new Path();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        clipCenterX = w / 2;
        clipCenterY = h / 2;
        if (isContentShown) {
            clipRadius = (float) (Math.sqrt(w * w + h * h) / 2);
        } else {
            clipRadius = 0;
        }
        super.onSizeChanged(w, h, oldW, oldH);
    }

    public float getClipRadius() {
        return clipRadius;
    }

    public void setClipRadius(float clipRadius) {
        this.clipRadius = clipRadius;
        invalidate();
    }

    public boolean isContentShown() {
        return isContentShown;
    }

    public void setContentShown(boolean isContentShown) {
        this.isContentShown = isContentShown;
        if (this.isContentShown) {
            clipRadius = 0;
        } else {
            clipRadius = getMaxRadius(clipCenterX, clipCenterY);
        }
        invalidate();
    }

    public void show() {
        show(DEFAULT_DURATION);
    }

    public void show(int duration) {
        show(duration, null);
    }

    public void show(int x, int y) {
        show(x, y, DEFAULT_DURATION, null);
    }

    public void show(Animation.AnimationListener listener) {
        show(DEFAULT_DURATION, listener);
    }

    public void show(int duration, Animation.AnimationListener listener) {
        show(getWidth() / 2, getHeight() / 2, duration, listener);
    }

    public void show(int x, int y, Animation.AnimationListener listener) {
        show(x, y, DEFAULT_DURATION, listener);
    }

    public void show(int x, int y, int duration) {
        show(x, y, duration, null);
    }

    public void show(int x, int y, int duration, final Animation.AnimationListener listener) {
        if (x < 0 || x > getWidth() || y < 0 || y > getHeight()) {
            throw new RuntimeException("Center point out of range or call method when View is not initialed yet.");
        }

        clipCenterX = x;
        clipCenterY = y;
        final float maxRadius = getMaxRadius(x, y);

        clearAnimation();

        animation = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setClipRadius(interpolatedTime * maxRadius);
            }

        };
        animation.setDuration(duration);
        animation.setInterpolator(new BakedBezierInterpolator());
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationRepeat(Animation animation) {
                if (listener != null) {
                    listener.onAnimationRepeat(animation);
                }
            }

            @Override
            public void onAnimationStart(Animation animation) {
                isContentShown = true;
                if (listener != null) {
                    listener.onAnimationStart(animation);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onAnimationEnd(animation);
                }
            }

        });
        startAnimation(animation);
    }

    public void hide() {
        hide(DEFAULT_DURATION);
    }

    public void hide(int duration) {
        hide(getWidth() / 2, getHeight() / 2, duration, null);
    }

    public void hide(int x, int y) {
        hide(x, y, DEFAULT_DURATION, null);
    }

    public void hide(Animation.AnimationListener listener) {
        hide(DEFAULT_DURATION, listener);
    }

    public void hide(int duration, Animation.AnimationListener listener) {
        hide(getWidth() / 2, getHeight() / 2, duration, listener);
    }

    public void hide(int x, int y, Animation.AnimationListener listener) {
        hide(x, y, DEFAULT_DURATION, listener);
    }

    public void hide(int x, int y, int duration) {
        hide(x, y, duration, null);
    }

    public void hide(int x, int y, int duration, final Animation.AnimationListener listener) {
        if (x < 0 || x > getWidth() || y < 0 || y > getHeight()) {
            throw new RuntimeException("Center point out of range or call method when View is not initialed yet.");
        }

        final float maxRadius = getMaxRadius(x, y);
        if (x != clipCenterX || y != clipCenterY) {
            clipCenterX = x;
            clipCenterY = y;
            clipRadius = maxRadius;
        }

        clearAnimation();

        animation = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setClipRadius(maxRadius * (1 - interpolatedTime));
            }

        };
        animation.setInterpolator(new BakedBezierInterpolator());
        animation.setDuration(duration);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                isContentShown = false;
                if (listener != null) {
                    listener.onAnimationStart(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if (listener != null) {
                    listener.onAnimationRepeat(animation);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null) {
                    listener.onAnimationEnd(animation);
                }
            }

        });
        startAnimation(animation);
    }

    public void next() {
        next(DEFAULT_DURATION);
    }

    public void next(int duration) {
        next(getWidth() / 2, getHeight() / 2, duration, null);
    }

    public void next(int x, int y) {
        next(x, y, DEFAULT_DURATION, null);
    }

    public void next(Animation.AnimationListener listener) {
        next(DEFAULT_DURATION, listener);
    }

    public void next(int duration, Animation.AnimationListener listener) {
        next(getWidth() / 2, getHeight() / 2, duration, listener);
    }

    public void next(int x, int y, Animation.AnimationListener listener) {
        next(x, y, DEFAULT_DURATION, listener);
    }

    public void next(int x, int y, int duration) {
        next(x, y, duration, null);
    }

    public void next(int x, int y, int duration, Animation.AnimationListener listener) {
        final int childCount = getChildCount();
        if (childCount > 1) {
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                if (i == 0) {
                    bringChildToFront(child);
                }
            }
            show(x, y, duration, listener);
        }
    }

    private float getMaxRadius(int x, int y) {
        int h = Math.max(x, getWidth() - x);
        int v = Math.max(y, getHeight() - y);
        return (float) Math.sqrt(h * h + v * v);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (indexOfChild(child) == getChildCount() - 1) {
            boolean result;
            clipPath.reset();
            clipPath.addCircle(clipCenterX, clipCenterY, clipRadius, Path.Direction.CW);

            canvas.save();
            canvas.clipPath(clipPath);
            result = super.drawChild(canvas, child, drawingTime);
            canvas.restore();
            return result;
        } else {
            return super.drawChild(canvas, child, drawingTime);
        }
    }

}
