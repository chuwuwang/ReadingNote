package widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import utils.DPUtil;


public class MoveAddBezierView extends TextView {

	private static final int SIZE = 20;

	private Paint mPaint;

	private int radius;

	private PointF startPos;
	private PointF endPos;

	public MoveAddBezierView(Context context) {
		this(context, null);
	}

	public MoveAddBezierView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MoveAddBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setColor(Color.RED);
		mPaint.setAntiAlias(true);

		setGravity(Gravity.CENTER);
		setText("1");
		setTextColor(Color.WHITE);
		setTextSize(12);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int px = (int) DPUtil.dp2px(getContext(), SIZE);
		radius = px / 2;
		setMeasuredDimension(px, px);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radius, mPaint);
		super.onDraw(canvas);
	}

	public void startMoveAddAnimation() {
		if (startPos == null || endPos == null) {
			return;
		}
		float pointX = (startPos.x + endPos.x) / 2;
		float pointY = startPos.y - DPUtil.dp2px(getContext(), 100);
		PointF controlPoint = new PointF(pointX, pointY);
		MoveAddEvaluator evaluator = new MoveAddEvaluator(controlPoint);
		ValueAnimator animator = ValueAnimator.ofObject(evaluator, startPos, endPos);
		animator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				PointF pointF = (PointF) animation.getAnimatedValue();
				setX(pointF.x);
				setY(pointF.y);
				invalidate();
			}

		});
		animator.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				ViewGroup parent = (ViewGroup) getParent();
				parent.removeView(MoveAddBezierView.this);
			}

		});
		animator.setDuration(500);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.start();
	}

	public void setStartPosition(PointF startPos) {
		startPos.y -= 10;
		this.startPos = startPos;
	}

	public void setEndPosition(PointF endPos) {
		this.endPos = endPos;
	}

	private class MoveAddEvaluator implements TypeEvaluator<PointF> {

		private PointF point;

		public MoveAddEvaluator(PointF point) {
			super();
			this.point = point;
		}

		@Override
		public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
			float x = (1 - fraction) * (1 - fraction) * startValue.x + 2 * fraction * (1 - fraction) * point.x
					+ fraction * fraction * endValue.x;
			float y = (1 - fraction) * (1 - fraction) * startValue.y + 2 * fraction * (1 - fraction) * point.y
					+ fraction * fraction * endValue.y;
			return new PointF(x, y);
		}

	}

}
