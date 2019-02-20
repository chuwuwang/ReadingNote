package widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

import com.nsz.android.utils.Adhesion;

import java.util.ArrayList;
import java.util.List;

/**
 * 粘合体进度条(菊花圈)
 *
 * @author Lee64
 */
public class AdhesionCircleLoadView extends View {

    private int width;
    private int height;

    private Paint paint;

    // 大圆
    private Circle bigCircle;
    private int bigCircleRadius = 50;
    // 静态圆
    private Circle staticCircle;
    // 动态圆
    private Circle dynamicCircle;
    // 维护静态圆容器
    private List<Circle> circleList;

    // 当前的静态圆半径
    private float currentStaticCircleRaidus = bigCircleRadius / 5;
    // 静态圆变化半径的最大比率
    private float rate = 0.4f;
    // 静态圆个数
    private int count = 8;
    // 最大粘连长度
    private float maxLength = 2.5f * currentStaticCircleRaidus;

    public AdhesionCircleLoadView(Context context) {
        super(context);
        init();
    }

    public AdhesionCircleLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdhesionCircleLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Style.FILL);
        paint.setColor(0xFF4DB9FF);

        width = height = (int) (2 * (bigCircleRadius + currentStaticCircleRaidus * (1 + rate)));

        // 大圆
        bigCircle = new Circle();
        bigCircle.x = width / 2;
        bigCircle.y = height / 2;
        bigCircle.radius = bigCircleRadius;

        // 动态圆
        dynamicCircle = new Circle();
        dynamicCircle.radius = currentStaticCircleRaidus * 3 / 4;
        dynamicCircle.x = bigCircle.x;
        dynamicCircle.y = currentStaticCircleRaidus * (1 + rate);

        circleList = new ArrayList<>();
        // 静态圆
        for (int i = 0; i < count; i++) {
            staticCircle = new Circle();
            staticCircle.radius = currentStaticCircleRaidus;
            staticCircle.x = (float) (bigCircle.x + bigCircleRadius * Math.cos(Math.toRadians(45 * i)));
            staticCircle.y = (float) (bigCircle.y + bigCircleRadius * Math.sin(Math.toRadians(45 * i)));
            circleList.add(staticCircle);
        }

        startAnim();
    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(-90, 270);
        animator.setDuration(2500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setRepeatCount(Animation.INFINITE);
        animator.start();
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float angle = (float) animation.getAnimatedValue();
                dynamicCircle.x = (float) (bigCircle.x + bigCircleRadius * Math.cos(Math.toRadians(angle)));
                dynamicCircle.y = (float) (bigCircle.y + bigCircleRadius * Math.sin(Math.toRadians(angle)));
                invalidate();
            }

        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = resolveSizeAndState(width, widthMeasureSpec, MeasureSpec.UNSPECIFIED);
        int h = resolveSizeAndState(height, heightMeasureSpec, MeasureSpec.UNSPECIFIED);
        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(dynamicCircle.x, dynamicCircle.y, dynamicCircle.radius, paint);
        for (int i = 0; i < count; i++) {
            staticCircle = circleList.get(i);
            // 判断哪个圆可以作贝塞尔曲线
            if (doAdhere(i)) {
                canvas.drawCircle(staticCircle.x, staticCircle.y, currentStaticCircleRaidus, paint);
                Path path = Adhesion.drawAdhesionBody(staticCircle.x, staticCircle.y, currentStaticCircleRaidus, 45,
                        dynamicCircle.x, dynamicCircle.y, dynamicCircle.radius, 45);
                canvas.drawPath(path, paint);
            } else {
                canvas.drawCircle(staticCircle.x, staticCircle.y, staticCircle.radius, paint);
            }
        }
    }

    /**
     * 判断粘连范围，动态改变静态圆大小
     */
    private boolean doAdhere(int position) {
        staticCircle = circleList.get(position);
        // 半径变化
        float distance = (float) Math
                .sqrt(Math.pow(dynamicCircle.x - staticCircle.x, 2) + Math.pow(dynamicCircle.y - staticCircle.y, 2));
        float scale = rate - rate * (distance / maxLength);
        currentStaticCircleRaidus = staticCircle.radius * (1 + scale);
        // 判断是否可以作贝塞尔曲线
        if (distance < maxLength) {
            return true;
        } else {
            return false;
        }
    }

    private class Circle {
        float x;
        float y;
        float radius;
    }

}
