package paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 设置拐角的形状。有三个值可以选择：MITER 尖角、 BEVEL 平角和 ROUND 圆角。默认为 MITER。
 *
 * @author Created by Lee64 on 2017/9/21.
 */

public class StrokeJoinView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Path path = new Path();

    public StrokeJoinView(Context context) {
        super(context);
    }

    public StrokeJoinView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeJoinView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStrokeWidth(40);
        paint.setStyle(Paint.Style.STROKE);
        path.rLineTo(200, 0);
        path.rLineTo(-160, 120);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        // 使用 Paint.setStrokeJoin() 来设置不同的拐角形状

        canvas.translate(50, 100);
        // 第一种形状：MITER
        paint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(path, paint);

        canvas.translate(300, 0);
        // 第二种形状：BEVEL
        paint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(path, paint);

        canvas.translate(300, 0);
        // 第三种形状：ROUND
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path, paint);

        canvas.restore();
    }

}
