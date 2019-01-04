package widget;

import java.util.ArrayList;
import java.util.List;


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

/**
 * 粘合体进度条(水平)
 * 
 * @author Lee64
 *
 */
public class AdhesionHorizontalLoadView extends View {

	private int width;
	private int height;

	private Paint paint;

	// 当前的静态圆半径
	private float currentStaticCircleRadius = 10f;
	// 静态圆变化半径的最大比率
	private float rate = 0.4f;
	// 静态圆个数
	private int count = 6;
	// 圆与圆之间的间隔距离
	private float spaceWidth = 3 * currentStaticCircleRadius;

	// 最大粘连长度
	private float maxLength = 3.5f * currentStaticCircleRadius;

	private Circle staticCircle;
	private Circle dynamicCircle;
	private List<Circle> circleList;

	public AdhesionHorizontalLoadView(Context context) {
		super(context);
		init();
	}

	public AdhesionHorizontalLoadView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public AdhesionHorizontalLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setColor(0xFF4DB9FF);
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);

		width = (int) ((count + 1) * (currentStaticCircleRadius * 2 + spaceWidth));
		height = (int) (2 * currentStaticCircleRadius * (1 + rate));

		// 动态圆
		dynamicCircle = new Circle();
		dynamicCircle.radius = currentStaticCircleRadius * 3 / 4;
		dynamicCircle.x = dynamicCircle.radius;
		dynamicCircle.y = height / 2;

		circleList = new ArrayList<>();
		// 静态圆
		for (int i = 0; i < count; i++) {
			staticCircle = new Circle();
			staticCircle.radius = currentStaticCircleRadius;
			staticCircle.x = (staticCircle.radius * 2 + spaceWidth) * (i + 1);
			staticCircle.y = height / 2;
			circleList.add(staticCircle);
		}

		startAnim();
	}

	private void startAnim() {
		ValueAnimator valueAnimator = ValueAnimator.ofFloat(dynamicCircle.x, width - dynamicCircle.radius);
		valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
		valueAnimator.setDuration(2500);
		valueAnimator.setRepeatCount(Animation.INFINITE);
		// 动画效果重复
		valueAnimator.setRepeatMode(Animation.REVERSE);
		valueAnimator.start();
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				dynamicCircle.x = (float) animation.getAnimatedValue();
				invalidate();
			}

		});
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 知识补充：方法resolveSizeAndState()
		// 用来创建最终的宽和高. 这个方法通过比较视图的期望大小返回一个合适的View.MeasureSpec值传入onMeasure()
		int w = resolveSizeAndState(width, widthMeasureSpec, MeasureSpec.UNSPECIFIED);
		int h = resolveSizeAndState(height, heightMeasureSpec, MeasureSpec.UNSPECIFIED);
		setMeasuredDimension(w, h);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawCircle(dynamicCircle.x, dynamicCircle.y, dynamicCircle.radius, paint);
		for (int i = 0; i < count; i++) {
			staticCircle = circleList.get(i);
			if (doAdhere(i)) {
				canvas.drawCircle(staticCircle.x, staticCircle.y, currentStaticCircleRadius, paint);
				Path path = Adhesion.drawAdhesionBody(staticCircle.x, staticCircle.y, currentStaticCircleRadius, 45,
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
		/* 半径变化 */
		float distance = (float) Math
				.sqrt(Math.pow(dynamicCircle.x - staticCircle.x, 2) + Math.pow(dynamicCircle.y - staticCircle.y, 2));
		float scale = rate - rate * (distance / maxLength);
		currentStaticCircleRadius = staticCircle.radius * (1 + scale);
		/* 判断是否可以作贝塞尔曲线 */
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
