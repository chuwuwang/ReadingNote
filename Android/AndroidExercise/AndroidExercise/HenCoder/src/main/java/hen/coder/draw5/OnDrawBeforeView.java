package hen.coder.draw5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.util.AttributeSet;

public class OnDrawBeforeView extends AppCompatTextView {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF mBounds = new RectF();

    {
        int color = Color.parseColor("#FFC107");
        mPaint.setColor(color);
    }

    public OnDrawBeforeView(Context context) {
        super(context);
    }

    public OnDrawBeforeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OnDrawBeforeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Layout layout = getLayout();
        mBounds.left = layout.getLineLeft(1);
        mBounds.right = layout.getLineRight(1);
        mBounds.top = layout.getLineTop(1);
        mBounds.bottom = layout.getLineBottom(1);
        canvas.drawRect(mBounds, mPaint);

        mBounds.left = layout.getLineLeft(layout.getLineCount() - 4);
        mBounds.right = layout.getLineRight(layout.getLineCount() - 4);
        mBounds.top = layout.getLineTop(layout.getLineCount() - 4);
        mBounds.bottom = layout.getLineBottom(layout.getLineCount() - 4);
        canvas.drawRect(mBounds, mPaint);

        super.onDraw(canvas);
    }


}
