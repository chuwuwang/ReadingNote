package widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * 闹钟表盘
 * 
 * @author Lee64
 *
 */
public class RotateClockView extends View {

	private static final int LONG_LINE_HEIGHT = 35;
	private static final int SHORT_LINE_HEIGHT = 25;

	private Paint mCirclePaint;
	private Paint mLinePaint;

	private int mHalfWidth;
	private int mHalfHeight;

	// 圆环线宽度
	private int mCircleLineWidth;
	private int mHalfCircleLineWidth;

	// 直线刻度线宽度
	private int mLineWidth;
	private int mHalfLineWidth;

	// 长线长度
	private int mLongLineHeight;

	// 短线长度
	private int mShortLineHeight;

	// 刻度线的左、上位置
	private int mLineLeft;
	private int mLineTop;
	// 刻度线的下边位置
	private int mLineBottom;

	// 用于控制刻度线位置
	private int mFixLineHeight;

	public RotateClockView(Context context) {
		super(context);
		init();
	}

	public RotateClockView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RotateClockView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		mCircleLineWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
				getResources().getDisplayMetrics());
		mHalfCircleLineWidth = mCircleLineWidth / 2;

		mLineWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
				getResources().getDisplayMetrics());
		mHalfLineWidth = mLineWidth / 2;

		mFixLineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
				getResources().getDisplayMetrics());

		mLongLineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, LONG_LINE_HEIGHT,
				getResources().getDisplayMetrics());

		mShortLineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, SHORT_LINE_HEIGHT,
				getResources().getDisplayMetrics());

		initPaint();
	}

	private void initPaint() {
		mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mCirclePaint.setColor(Color.RED);
		mCirclePaint.setStyle(Style.STROKE);
		mCirclePaint.setStrokeWidth(mCircleLineWidth);

		mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mLinePaint.setColor(Color.RED);
		mLinePaint.setStyle(Style.FILL_AND_STROKE);
		mLinePaint.setStrokeWidth(mLineWidth);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mHalfWidth = w / 2;
		mHalfHeight = h / 2;

		mLineLeft = mHalfWidth - mHalfLineWidth;
		mLineTop = mHalfHeight - mHalfWidth + mFixLineHeight;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 绘制表盘
		drawCircle(canvas);
		// 绘制刻度
		drawLines(canvas);
	}

	private void drawLines(Canvas canvas) {
		for (int i = 0; i < 360; i++) {

			if (i % 30 == 0) {
				mLineBottom = mLineTop + mLongLineHeight;
				mLinePaint.setStrokeWidth(mLineWidth);
			} else {
				mLineBottom = mLineTop + mShortLineHeight;
				mLinePaint.setStrokeWidth(mHalfLineWidth);
			}

			if (i % 6 == 0) {
				canvas.save();
				canvas.rotate(i, mHalfWidth, mHalfHeight);
				canvas.drawLine(mLineLeft, mLineTop, mLineLeft, mLineBottom, mLinePaint);
				canvas.restore();
			}

		}
	}

	private void drawCircle(Canvas canvas) {
		canvas.drawCircle(mHalfWidth, mHalfHeight, mHalfWidth - mHalfCircleLineWidth, mCirclePaint);
	}

}
