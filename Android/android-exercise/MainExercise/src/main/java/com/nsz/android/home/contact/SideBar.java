package com.nsz.android.home.contact;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class SideBar extends View {

    public static String[] b = {
            "↑", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z", "#"
    };

    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;

    private int choose = -1;
    private Paint paint = new Paint();

    private TextView textDialog;

    public void setTextView(TextView textView) {
        this.textDialog = textView;
    }

    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideBar(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int singleHeight = height / b.length;
        for (int i = 0; i < b.length; i++) {
            int color = Color.parseColor("#181818");
            paint.setColor(color);
            paint.setTextSize(18);
            paint.setAntiAlias(true);
            // paint.setTypeface(Typeface.DEFAULT_BOLD);
            // 选中的状态
            if (i == choose) {
                color = Color.parseColor("#08C161");
                paint.setColor(color);
                paint.setFakeBoldText(true);
            }
            String value = b[i];
            boolean equals = TextUtils.equals("↑", value);
            if (equals) {
                paint.setTextSize(24);
            }
            // x坐标等于中间-字符串宽度的一半
            float xPos = width / 2 - paint.measureText(value) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(value, xPos, yPos, paint);
            paint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() * b.length); // 点击y坐标所占总高度的比例 * b数组的长度就等于点击b中的个数
        switch (action) {
            case MotionEvent.ACTION_UP:
                // ColorDrawable drawable = new ColorDrawable(0x00000000);
                // setBackgroundDrawable(drawable);
                choose = -1;
                invalidate();
                if (textDialog != null) {
                    textDialog.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                // setBackgroundResource(R.drawable.bg_sidebar);
                if (oldChoose != c && c >= 0 && c < b.length) {
                    String value = b[c];
                    if (listener != null) {
                        listener.onTouchingLetterChanged(value);
                    }
                    if (textDialog != null) {
                        textDialog.setText(value);
                        textDialog.setVisibility(View.VISIBLE);
                    }
                    choose = c;
                    invalidate();
                }
                break;
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener l) {
        this.onTouchingLetterChangedListener = l;
    }

    public interface OnTouchingLetterChangedListener {

        void onTouchingLetterChanged(String s);

    }


}