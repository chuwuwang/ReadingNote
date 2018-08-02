package paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这个方法是对于 setStrokeJoin() 的一个补充，它用于设置 MITER 型拐角的延长线的最大值。所谓「延长线的最大值」，是这么一回事：
 * 当线条拐角为 MITER 时，拐角处的外缘需要使用延长线来补偿：
 *
 * @author Created by Lee64 on 2017/9/21.
 */

public class StrokeMiterView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path path = new Path();


    public StrokeMiterView(Context context) {
        super(context);
    }

    public StrokeMiterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeMiterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        canvas.translate(100, 100);
        // MITER 值：1
        paint.setStrokeMiter(1);
        canvas.drawPath(path, paint);

        canvas.translate(300, 0);
        // MITER 值：2
        paint.setStrokeMiter(2);
        canvas.drawPath(path, paint);

        canvas.translate(300, 0);
        // MITER 值：5
        paint.setStrokeMiter(5);
        canvas.drawPath(path, paint);

        canvas.restore();

    }

}
