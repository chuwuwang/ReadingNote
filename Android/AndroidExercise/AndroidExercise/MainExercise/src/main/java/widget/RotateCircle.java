package widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class RotateCircle extends View {

	private Paint mPaint;

	public RotateCircle(Context context) {
		super(context);
		init();
	}

	public RotateCircle(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RotateCircle(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.STROKE);
		mPaint.setColor(Color.BLUE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int width = canvas.getWidth();
		int height = canvas.getHeight();
		// 将坐标系原点移动到画布正中心
		canvas.translate(width / 2, height / 2);

		canvas.drawCircle(0, 0, 300, mPaint);
		canvas.drawCircle(0, 0, 280, mPaint);

		for (int i = 0; i <= 360; i += 10) {
			canvas.drawLine(0, 280, 0, 300, mPaint);
			canvas.rotate(10);
		}

	}

}
