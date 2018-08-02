package widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 缩放的矩形
 * 
 * @author Lee64
 *
 */
public class ScaleRect extends View {

	private Paint mPaint;

	public ScaleRect(Context context) {
		super(context);
		init();
	}

	public ScaleRect(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ScaleRect(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLACK);
		mPaint.setStyle(Style.STROKE);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int width = canvas.getWidth();
		int height = canvas.getHeight();
		// 将坐标系原点移动到画布正中心
		canvas.translate(width / 2, height / 2);

		RectF f = new RectF(-300, -300, 300, 300);

		for (int i = 0; i < 20; i++) {
			canvas.scale(0.9f, 0.9f);
			canvas.drawRect(f, mPaint);
		}
	}

}
