package hen.coder.draw5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class OnDrawAfterView extends AppCompatImageView {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    {
        int color = Color.parseColor("#FFC107");
        mPaint.setColor(color);
        mPaint.setTextSize(28);
    }

    public OnDrawAfterView(Context context) {
        super(context);
    }

    public OnDrawAfterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OnDrawAfterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 在 super.onDraw() 的下方插入绘制代码，让绘制内容盖住原主体内容
        // 由于这期的重点是绘制代码的位置而不是绘制代码本身，所以直接给出绘制代码，你只要解除注释就好
        // 爽吧？
        Drawable drawable = getDrawable();
        if (drawable != null) {
            canvas.save();
            Matrix matrix = getImageMatrix();
            canvas.concat(matrix);
            Rect bounds = drawable.getBounds();
            String size = bounds.width() + " x " + bounds.height();
            canvas.drawText(size, 20, 40, mPaint);
            canvas.restore();
        }
    }


}
