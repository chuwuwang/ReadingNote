package widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.nsz.android.R;

public class CheckView extends View {

    private static final int ANIM_NULL = 0;    // 动画状态-没有
    private static final int ANIM_CHECK = 1;   // 动画状态-开启
    private static final int ANIM_END = 2; // 动画状态-结束

    private int mWidth, mHeight;

    private Handler mHandler;

    private Paint mPaint;
    private Bitmap okBitmap;

    private int animCurrentPage = -1;  // 当前页码
    private int animMaxPage = 13;       // 总页数
    private int animDuration = 500;    // 动画时长
    private int animState = ANIM_NULL; // 动画状态

    private boolean isCheck = false; // 是否只选中状态

    public CheckView(Context context) {
        super(context);
        init();
    }

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("HandlerLeak")
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Style.FILL);
        mPaint.setAntiAlias(true);

        okBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.check_mark);

        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (animCurrentPage < animMaxPage && animCurrentPage >= 0) {
                    invalidate();
                    if (animState == ANIM_NULL) {
                        return;
                    }
                    if (animState == ANIM_CHECK) {
                        animCurrentPage++;
                    } else if (animState == ANIM_END) {
                        animCurrentPage--;
                    }
                    this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);

                } else {
                    if (isCheck) {
                        animCurrentPage = animCurrentPage - 1;
                    } else {
                        animCurrentPage = -1;
                    }
                    invalidate();
                    animState = ANIM_NULL;
                }

            }

        };

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        mWidth = w;
        mHeight = h;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawCircle(0, 0, 140, mPaint);

        int sideLength = okBitmap.getHeight();
        Rect src = new Rect(sideLength * animCurrentPage, 0, sideLength * (animCurrentPage + 1), sideLength);
        Rect dst = new Rect(-100, -100, 100, 100);

        canvas.drawBitmap(okBitmap, src, dst, null);
    }

    /**
     * 选择
     */
    public void check() {
        if (animState != ANIM_NULL || isCheck) {
            return;
        }
        animState = ANIM_CHECK;
        animCurrentPage = 0;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = true;
    }

    /**
     * 取消选择
     */
    public void unCheck() {
        if (animState != ANIM_NULL || !isCheck) {
            return;
        }
        animState = ANIM_END;
        animCurrentPage = animMaxPage - 1;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = false;
    }

    /**
     * 设置动画时长
     */
    public void setAnimDuration(int animDuration) {
        if (animDuration <= 0) {
            return;
        }
        this.animDuration = animDuration;
    }

    /**
     * 设置背景圆形颜色
     */
    public void setBackgroundColor(int color) {
        mPaint.setColor(color);
    }

}
