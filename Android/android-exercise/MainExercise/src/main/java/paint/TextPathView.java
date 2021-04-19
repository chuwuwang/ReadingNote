package paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 「文字的 Path」。文字的绘制，虽然是使用 Canvas.drawText()方法，但其实在下层，文字信息全是被转化成图形，对图形进行绘制的。
 * getTextPath() 方法，获取的就是目标文字所对应的 Path 。
 * 这个就是所谓「文字的 Path」。
 */
public class TextPathView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path textPath = new Path();
    private String text = "Hello HenCoder";

    public TextPathView(Context context) {
        super(context);
    }

    public TextPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextSize(120);

        // 使用 Paint.getTextPath() 来获取文字的 Path

        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(text, 50, 200, paint);

        paint.getTextPath(text, 0, 3, 50, 200, textPath);

        canvas.drawPath(textPath, pathPaint);
    }

}
