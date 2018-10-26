package hen.coder.draw5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class OnDrawForegroundAfterView extends AppCompatImageView {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public OnDrawForegroundAfterView(Context context) {
        super(context);
    }

    public OnDrawForegroundAfterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OnDrawForegroundAfterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);

        canvas.save();
        Matrix matrix = getImageMatrix();
        canvas.concat(matrix);
        int color = Color.parseColor("#FF0000");
        mPaint.setColor(color);
        mPaint.setTextSize(24);
        canvas.drawRect(0, 32, 120, 96, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawText("Hot", 16, 72, mPaint);
        canvas.restore();

    }


}
