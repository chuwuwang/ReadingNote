package hen.coder.draw5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DispatchDrawBeforeLinearLayout extends LinearLayout {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public DispatchDrawBeforeLinearLayout(Context context) {
        super(context);
    }

    public DispatchDrawBeforeLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchDrawBeforeLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        int color = Color.parseColor("#A0E91E63");
        mPaint.setColor(color);

        canvas.save();
        ImageView imageView = (ImageView) getChildAt(0);
        Matrix matrix = imageView.getImageMatrix();
        canvas.concat(matrix);
        canvas.drawCircle(32, 32, 16, mPaint);
        canvas.drawCircle(32, 64, 24, mPaint);
        canvas.drawCircle(112, 32, 32, mPaint);
        canvas.drawCircle(14, 144, 8, mPaint);
        canvas.drawCircle(256, 144, 56, mPaint);
        canvas.drawCircle(300, 300, 48, mPaint);
        canvas.restore();

    }


}
