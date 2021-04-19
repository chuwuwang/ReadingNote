package hen.coder.draw5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class DrawBeforeView extends AppCompatEditText {

    public DrawBeforeView(Context context) {
        super(context);
    }

    public DrawBeforeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawBeforeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        int color = Color.parseColor("#66BB6A");
        canvas.drawColor(color); // 涂上绿色

        super.draw(canvas);
    }

}
