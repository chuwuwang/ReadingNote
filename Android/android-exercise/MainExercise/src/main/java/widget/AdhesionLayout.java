package widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.RelativeLayout;

/**
 * 粘附行布局
 *
 * @author Lee64
 */
public class AdhesionLayout extends RelativeLayout {

    private Paint paint;
    private Path path;
    // 粘连的颜色
    private int color;

    private int width;
    private int height;

    // 用户添加的视图（可以不添加）
    private View view;

    // 本View的左上角x、y
    private float topX;
    private float topY;

    // 记录按下的x、y
    private float downX;
    private float downY;

    // 按下的时候，记录此刻尾部圆的圆心x、y
    private float downFootCircleX;
    private float downFootCircleY;

    // 头部圆的当前半径
    private float currentRadius;
    private Circle headerCircle;
    private Circle footerCircle;

    // 默认粘连的最大长度
    private static float MAX_HEADER_LENGTH = 300;
    // 头部圆缩小时不能小于这个最小半径
    private static float MIN_HEADER_CIRCLE_RADIUS = 3;

    // 是否第一次onSizeChanged
    private boolean isFirstSizeChange = true;
    // 是否正在进行动画中
    private boolean isAnimRunning = false;
    // 是否粘连着
    private boolean isAdherent = true;
    // 是否允许可以扯断
    private boolean isSnap = false;

    private OnAdherentListener onAdherentListener;

    /**
     * 设置是否可以扯断
     */
    public void setSnapEnable(boolean isSnap) {
        this.isSnap = isSnap;
    }

    public void setOnAdherentListener(OnAdherentListener onAdherentListener) {
        this.onAdherentListener = onAdherentListener;
    }

    public AdhesionLayout(Context context) {
        super(context);
        init();
    }

    public AdhesionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdhesionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 透明背景
        setBackgroundColor(Color.TRANSPARENT);
        // 设置颜色
        color = Color.rgb(247, 82, 49);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        path = new Path();
        headerCircle = new Circle();
        footerCircle = new Circle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        if (isFirstSizeChange && w > 0 && h > 0) {
            isFirstSizeChange = false;
            view = getChildAt(0);
            width = w;
            height = h;
            topX = getX();
            topY = getY();
            reset();
        }
    }

    public void reset() {
        setWidthAndHeight(width, height);
        currentRadius = headerCircle.radius = footerCircle.radius = (float) (Math.min(width, height) / 2) - 2;
        headerCircle.x = footerCircle.x = width / 2;
        headerCircle.y = footerCircle.y = height / 2;
        if (view != null) {
            view.setX(0);
            view.setY(0);
        }
        isAnimRunning = false;
    }

    private void setWidthAndHeight(int w, int h) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = w;
        layoutParams.height = h;
        setLayoutParams(layoutParams);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawCircle(headerCircle.x, headerCircle.y, currentRadius, paint);
        canvas.drawCircle(footerCircle.x, footerCircle.y, footerCircle.radius, paint);
        if (isAdherent) {
            drawBezier(canvas);
        }
    }

    private void drawBezier(Canvas canvas) {
        // a_tan : 反正切值 因为tan(a_tanA)=A 互为反函数 所以 a_tanA = 角度值
        // double a_tan(double d) 返回对应角度的反正切值
        // a_tan为角度值
        float a_tan = (float) Math.tan((footerCircle.y - headerCircle.y) / (footerCircle.x - headerCircle.x));
        // 获取sin、cos值
        float sin = (float) Math.sin(a_tan);
        float cos = (float) Math.cos(a_tan);

        float headerX1 = headerCircle.x - currentRadius * sin;
        float headerY1 = headerCircle.y + currentRadius * cos;

        float headerX2 = headerCircle.x + currentRadius * sin;
        float headerY2 = headerCircle.y - currentRadius * cos;

        float footerX1 = footerCircle.x - footerCircle.radius * sin;
        float footerY1 = footerCircle.y + footerCircle.radius * cos;

        float footerX2 = footerCircle.x + footerCircle.radius * sin;
        float footerY2 = footerCircle.y - footerCircle.radius * cos;

        // 控制点
        float anchorX = (headerCircle.x + footerCircle.x) / 2;
        float anchorY = (headerCircle.y + footerCircle.y) / 2;

        path.reset();
        path.moveTo(headerX1, headerY1);
        path.quadTo(anchorX, anchorY, footerX1, footerY1);
        path.lineTo(footerX2, footerY2);

        path.quadTo(anchorX, anchorY, headerX2, headerY2);
        path.lineTo(headerX1, headerY1);

        canvas.drawPath(path, paint);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isAnimRunning) {
            return true;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setWidthAndHeight(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

                headerCircle.x = topX + headerCircle.x;
                headerCircle.y = topY + headerCircle.y;
                footerCircle.x = topX + footerCircle.x;
                footerCircle.y = topY + footerCircle.y;

                downX = topX + event.getX();
                downY = topY + event.getY();

                downFootCircleX = footerCircle.x;
                downFootCircleY = footerCircle.y;

                if (view != null) {
                    view.setX(footerCircle.x - width / 2);
                    view.setY(footerCircle.y - height / 2);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                footerCircle.x = downFootCircleX + (event.getX() - downX);
                footerCircle.y = downFootCircleY + (event.getY() - downY);
                if (view != null) {
                    view.setX(footerCircle.x - width / 2);
                    view.setY(footerCircle.y - height / 2);
                }
                doAdhere();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isAnimRunning = true;
                if (isAdherent) {
                    startAnim();
                } else {
                    footerCircle.radius = 0;
                    if (onAdherentListener != null) {
                        onAdherentListener.onDismiss();
                    }
                }
                break;
            default:
                break;
        }

        invalidate();
        return true;
    }

    /**
     * 处理粘连效果逻辑
     */
    private void doAdhere() {
        // Math.sqrt(x) 返回x的平方根
        // Math.pow(x,y) 返回x的y次方
        float distance = (float) Math
                .sqrt(Math.pow(footerCircle.x - headerCircle.x, 2) + Math.pow(footerCircle.y - headerCircle.y, 2));
        float scale = 1 - distance / MAX_HEADER_LENGTH;
        currentRadius = Math.max(headerCircle.radius * scale, MIN_HEADER_CIRCLE_RADIUS);

        // 如果拉扯距离大于设定的最大距离并且此时设置了可以扯断
        if (distance > MAX_HEADER_LENGTH && isSnap) {
            isAdherent = false;
            currentRadius = 0;
        } else {
            isAdherent = true;
        }
    }

    private void startAnim() {
        ValueAnimator animatorX = ValueAnimator.ofFloat(footerCircle.x, topX + width / 2);
        animatorX.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                footerCircle.x = (float) animation.getAnimatedValue();
                invalidate();
            }

        });
        ValueAnimator animatorY = ValueAnimator.ofFloat(footerCircle.y, topY + height / 2);
        animatorY.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                footerCircle.y = (float) animation.getAnimatedValue();
                invalidate();
            }

        });

        // 动画集合
        AnimatorSet animatorSet = new AnimatorSet();
        if (view != null) {
            PropertyValuesHolder pvX = PropertyValuesHolder.ofFloat("X", footerCircle.x - width / 2, topX);
            PropertyValuesHolder pvY = PropertyValuesHolder.ofFloat("Y", footerCircle.y - height / 2, topY);
            ObjectAnimator animatorObj = ObjectAnimator.ofPropertyValuesHolder(view, pvX, pvY);
            animatorSet.play(animatorX).with(animatorY).with(animatorObj);
        } else {
            animatorSet.play(animatorX).with(animatorY);
        }
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.setDuration(400);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                reset();
            }

        });
    }

    public interface OnAdherentListener {

        void onDismiss();

    }

    private class Circle {
        float x;
        float y;
        float radius;
    }

}
