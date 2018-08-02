package widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 太极圈
 * 
 * @author lee.shenzhou
 * 
 */
public class TaiChiView extends View {

	private Paint whitePaint; // 白色画笔
	private Paint blackPaint; // 黑色画笔
	
	private float degrees;  // 旋转角度
	
	public void setRotate(float degrees) {
		this.degrees = degrees;
		invalidate();
	}
	
	public TaiChiView(Context context) {
		this(context, null, 0);
	}
	
	public TaiChiView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public TaiChiView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		whitePaint = new Paint();
		whitePaint.setAntiAlias(true);
		whitePaint.setColor(Color.WHITE);
		
		blackPaint = new Paint();
		blackPaint.setAntiAlias(true);
		blackPaint.setColor(Color.BLACK);
	}
	
	@SuppressLint("DrawAllocation") 
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		int width = canvas.getWidth();   // 画布宽度
		int height = canvas.getHeight(); // 画布高度
		
		canvas.translate(width/ 2, height / 2); // 移动坐标原点
		
		canvas.drawColor(Color.GRAY); // 绘制背景颜色
		canvas.rotate(degrees);	// 旋转画布
		
		// 绘制2个半圆
		int radius = Math.min(width, height) / 2 - 100; // 太极半径
		RectF rectF = new RectF(-radius, -radius, radius, radius); // 绘制区域
		canvas.drawArc(rectF, 90, 180, true, blackPaint);  // 绘制黑色半圆
		canvas.drawArc(rectF, -90, 180, true, whitePaint); // 绘制白色半圆
		
		// 绘制2个小圆
		int smallRadius = radius / 2; // 小圆半径为大圆的一半
		canvas.drawCircle(0, -smallRadius, smallRadius, blackPaint);
		canvas.drawCircle(0, smallRadius, smallRadius, whitePaint);
		
		// 绘制鱼眼（两个更小的圆）
		canvas.drawCircle(0, -smallRadius, smallRadius / 4, whitePaint);
		canvas.drawCircle(0, smallRadius, smallRadius / 4, blackPaint);
	}
	
	
	
}
