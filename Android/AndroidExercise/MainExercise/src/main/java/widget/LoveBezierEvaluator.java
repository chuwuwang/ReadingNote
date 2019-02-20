package widget;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

public class LoveBezierEvaluator implements TypeEvaluator<PointF> {

	private PointF pointF1;
	private PointF pointF2;

	public LoveBezierEvaluator(PointF pointF1, PointF pointF2) {
		super();
		this.pointF1 = pointF1;
		this.pointF2 = pointF2;
	}

	@Override
	public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
		PointF pointF = new PointF();
		pointF.x = startValue.x * (1 - fraction) * (1 - fraction) * (1 - fraction) + 
				3 * pointF1.x * fraction * (1 - fraction) * (1 - fraction) + 
				3 * pointF2.x * fraction * fraction * (1 - fraction) + 
				endValue.x * fraction * fraction * fraction;
		pointF.y = startValue.y * (1 - fraction) * (1 - fraction) * (1 - fraction) + 
				3 * pointF1.y * fraction * (1 - fraction) * (1 - fraction) + 
				3 * pointF2.y * fraction * fraction * (1 - fraction) + 
				endValue.y * fraction * fraction * fraction;
		return pointF;
	}

}
