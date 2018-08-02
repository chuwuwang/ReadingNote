package widget;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Created by Lee64 on 2017/10/10.
 */

public class ShapeLoadView extends View {

    private Paint paint;

    private int triangleColor;
    private int circleColor;
    private int rectColor;

    private float controlX = 0;
    private float controlY = 0;
    // 赛贝尔曲线
    private static final float magicNumber = 0.55228475f;
    private static final float sqr3 = 1.7320508075689f;
    private static final float triangle2Circle = 0.25555555f;

    private float animPercent;
    private boolean isLoading;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    private Shape shape;

    public enum Shape {
        TRIANGLE, CIRCLE, RECT;
    }

    public Shape getShape() {
        return shape;
    }

    public void changeShape() {
        isLoading = true;
        invalidate();
    }

    public ShapeLoadView(Context context) {
        super(context);
        init();
    }

    public ShapeLoadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShapeLoadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        triangleColor = Color.parseColor("#aa72d572");
        circleColor = Color.parseColor("#aa738ffe");
        rectColor = Color.parseColor("#aae84e40");

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        // 先画圆
        shape = Shape.CIRCLE;
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == VISIBLE) {
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getVisibility() == GONE) return;
        switch (shape) {
            case TRIANGLE:
                drawTriangle(canvas);
                break;
            case CIRCLE:
                drawCircle(canvas);
                break;
            case RECT:
                drawRect(canvas);
                break;
        }
    }

    private void drawTriangle(Canvas canvas) {
        Path path = new Path();
        if (isLoading) {
            animPercent += 0.1611113;
            int color = (int) argbEvaluator.evaluate(animPercent, triangleColor, circleColor);
            paint.setColor(color);
            if (animPercent >= 1f) {
                shape = Shape.CIRCLE;
                isLoading = false;
                animPercent = 1;
            }
            path.moveTo(
                    relativeXFromView(0.5f),
                    relativeYFromView(0f)
            );
            float x = controlX - relativeXFromView(animPercent * triangle2Circle) * sqr3;
            float y = controlY - relativeYFromView(animPercent * triangle2Circle);
            path.quadTo(
                    relativeXFromView(1) - x,
                    y,
                    relativeXFromView(0.5f + sqr3 / 4),
                    relativeYFromView(0.75f)
            );
            path.quadTo(
                    relativeXFromView(0.5f),
                    relativeYFromView(0.75f + 2 * animPercent * triangle2Circle),
                    relativeXFromView(0.5f - sqr3 / 4),
                    relativeYFromView(0.75f)
            );
            path.quadTo(
                    x,
                    y,
                    relativeXFromView(0.5f),
                    relativeYFromView(0f)
            );
            path.close();
            canvas.drawPath(path, paint);
            invalidate();
        } else {
            paint.setColor(triangleColor);
            path.moveTo(
                    relativeXFromView(0.5f),
                    relativeYFromView(0f)
            );
            path.lineTo(
                    relativeXFromView(1),
                    relativeYFromView(sqr3 / 2f)
            );
            path.lineTo(
                    relativeXFromView(0),
                    relativeYFromView(sqr3 / 2f)
            );
            controlX = relativeXFromView(0.5f - sqr3 / 8.0f);
            controlY = relativeYFromView(3 / 8.0f);
            path.close();
            canvas.drawPath(path, paint);
            animPercent = 0;
        }
    }

    private void drawCircle(Canvas canvas) {
        final float magic = magicNumber;
        Path path = new Path();
        if (isLoading) {
            animPercent += 0.12;
            if (magic + animPercent >= 1.9f) {
                shape = Shape.RECT;
                isLoading = false;
            }
            int color = (int) argbEvaluator.evaluate(animPercent, circleColor, rectColor);
            paint.setColor(color);
            path.moveTo(
                    relativeXFromView(0.5f),
                    relativeYFromView(0f)
            );
            path.cubicTo(
                    relativeXFromView(0.5f + magic / 2),
                    relativeYFromView(0f),
                    relativeXFromView(1),
                    relativeYFromView(0.5f - magic / 2),
                    relativeXFromView(1f),
                    relativeYFromView(0.5f)
            );
            path.cubicTo(
                    relativeXFromView(1),
                    relativeXFromView(0.5f + magic / 2),
                    relativeXFromView(0.5f + magic / 2),
                    relativeYFromView(1f),
                    relativeXFromView(0.5f),
                    relativeYFromView(1f)
            );
            path.cubicTo(
                    relativeXFromView(0.5f - magic / 2),
                    relativeXFromView(1f),
                    relativeXFromView(0),
                    relativeYFromView(0.5f + magic / 2),
                    relativeXFromView(0f),
                    relativeYFromView(0.5f)
            );
            path.cubicTo(
                    relativeXFromView(0f),
                    relativeXFromView(0.5f - magic / 2),
                    relativeXFromView(0.5f - magic / 2),
                    relativeYFromView(0),
                    relativeXFromView(0.5f),
                    relativeYFromView(0f)
            );
            path.close();
            canvas.drawPath(path, paint);
            invalidate();
        } else {
            paint.setColor(circleColor);
            path.moveTo(
                    relativeXFromView(0.5f),
                    relativeYFromView(0f)
            );
            path.cubicTo(
                    relativeXFromView(0.5f + magic / 2),
                    0,
                    relativeXFromView(1),
                    relativeYFromView(magic / 2),
                    relativeXFromView(1f),
                    relativeYFromView(0.5f)
            );
            path.cubicTo(
                    relativeXFromView(1),
                    relativeXFromView(0.5f + magic / 2),
                    relativeXFromView(0.5f + magic / 2),
                    relativeYFromView(1f),
                    relativeXFromView(0.5f),
                    relativeYFromView(1f)
            );
            path.cubicTo(
                    relativeXFromView(0.5f - magic / 2),
                    relativeXFromView(1f),
                    relativeXFromView(0),
                    relativeYFromView(0.5f + magic / 2),
                    relativeXFromView(0f),
                    relativeYFromView(0.5f)
            );
            path.cubicTo(
                    relativeXFromView(0f),
                    relativeXFromView(0.5f - magic / 2),
                    relativeXFromView(0.5f - magic / 2),
                    relativeYFromView(0),
                    relativeXFromView(0.5f),
                    relativeYFromView(0f));
            path.close();
            canvas.drawPath(path, paint);
            animPercent = 0;
        }
    }

    private void drawRect(Canvas canvas) {
        Path path = new Path();
        if (isLoading) {
            animPercent += 0.15;
            if (animPercent >= 1) {
                shape = Shape.TRIANGLE;
                isLoading = false;
                animPercent = 1;
            }
            int color = (int) argbEvaluator.evaluate(animPercent, circleColor, triangleColor);
            paint.setColor(color);
            path.moveTo(
                    relativeXFromView(0.5f * animPercent),
                    0
            );
            path.lineTo(
                    relativeYFromView(1 - 0.5f * animPercent),
                    0)
            ;
            float distanceX = (controlX) * animPercent;
            float distanceY = (relativeYFromView(1f) - controlY) * animPercent;
            path.lineTo(
                    relativeXFromView(1f) - distanceX,
                    relativeYFromView(1f) - distanceY
            );
            path.lineTo(
                    relativeXFromView(0f) + distanceX,
                    relativeYFromView(1f) - distanceY
            );
            path.close();
            canvas.drawPath(path, paint);
            invalidate();
        } else {
            paint.setColor(rectColor);
            controlX = relativeXFromView(0.5f - sqr3 / 4);
            controlY = relativeYFromView(0.75f);
            path.moveTo(
                    relativeXFromView(0f),
                    relativeYFromView(0f)
            );
            path.lineTo(
                    relativeXFromView(1f),
                    relativeYFromView(0f)
            );
            path.lineTo(
                    relativeXFromView(1f),
                    relativeYFromView(1f)
            );
            path.lineTo(
                    relativeXFromView(0f),
                    relativeYFromView(1f)
            );
            path.close();
            canvas.drawPath(path, paint);
            animPercent = 0;
        }
    }

    private float relativeXFromView(float percent) {
        return getWidth() * percent;
    }

    private float relativeYFromView(float percent) {
        return getHeight() * percent;
    }


}
