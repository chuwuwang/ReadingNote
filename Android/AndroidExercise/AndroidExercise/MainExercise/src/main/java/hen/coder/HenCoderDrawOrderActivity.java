package hen.coder;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nsz.android.R;

import hen.coder.draw5.DispatchDrawBeforeLinearLayout;
import hen.coder.draw5.DrawAfterView;
import hen.coder.draw5.DrawBeforeView;
import hen.coder.draw5.OnDrawAfterView;
import hen.coder.draw5.OnDrawBeforeView;
import hen.coder.draw5.OnDrawForegroundAfterView;
import hen.coder.draw5.OnDrawForegroundBeforeView;
import com.nsz.android.home.BaseAppCompatActivity;

public class HenCoderDrawOrderActivity extends BaseAppCompatActivity {

    private LinearLayout mContainer;
    private OnDrawAfterView mOnDrawAfterView;
    private OnDrawBeforeView mOnDrawBeforeView;

    private OnDrawForegroundAfterView mOnDrawForegroundAfterView;
    private OnDrawForegroundBeforeView mOnDrawForegroundBeforeView;

    private DrawAfterView mDrawAfterView;
    private DrawBeforeView mDrawBeforeView;

    private DispatchDrawBeforeLinearLayout mDispatchDrawBeforeLinearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hen_coder_activity_draw_order);
        initToolbarBringBack();
        initView();
    }

    private void initView() {
        mContainer = findViewById(R.id.container);

        mOnDrawAfterView = new OnDrawAfterView(this);
        mOnDrawAfterView.setImageResource(R.drawable.batman);
        mContainer.addView(mOnDrawAfterView);

        mOnDrawBeforeView = new OnDrawBeforeView(this);
        mOnDrawBeforeView.setText(
                "HenCoder 是\n" +
                        "针对高级 Android 工程师\n" +
                        "的进阶手册\n" +
                        "旨在突破高手们最基础的技能瓶颈\n" +
                        "结束止步不前的状态，继续快速提升\n" +
                        "内容难度上也许对新手不够友好\n" +
                        "只能说句得罪了\n" +
                        "我个人水平有限\n" +
                        "照顾不到所有人"
        );

        int color = Color.parseColor("#88000000");
        ColorDrawable colorDrawable = new ColorDrawable(color);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.batman);

        mOnDrawForegroundAfterView = new OnDrawForegroundAfterView(this);
        mOnDrawForegroundAfterView.setImageResource(R.drawable.batman);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mOnDrawForegroundAfterView.setForeground(colorDrawable);
        }

        mOnDrawForegroundBeforeView = new OnDrawForegroundBeforeView(this);
        mOnDrawForegroundBeforeView.setImageResource(R.drawable.batman);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mOnDrawForegroundBeforeView.setForeground(colorDrawable);
        }

        mDrawAfterView = new DrawAfterView(this);
        mDrawAfterView.setText("Hello HenCoder");

        mDrawBeforeView = new DrawBeforeView(this);
        mDrawBeforeView.setText("Hello HenCoder");

        mDispatchDrawBeforeLinearLayout = new DispatchDrawBeforeLinearLayout(this);
        mDispatchDrawBeforeLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mDispatchDrawBeforeLinearLayout.addView(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hen_coder_menu_draw_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        switch (itemId) {
            case R.id.on_draw_after:
                mContainer.removeAllViews();
                mContainer.addView(mOnDrawAfterView);
                break;
            case R.id.on_draw_before:
                mContainer.removeAllViews();
                mContainer.addView(mOnDrawBeforeView);
                break;
            case R.id.on_draw_foreground_after:
                mContainer.removeAllViews();
                mContainer.addView(mOnDrawForegroundAfterView);
                break;
            case R.id.on_draw_foreground_before:
                mContainer.removeAllViews();
                mContainer.addView(mOnDrawForegroundBeforeView);
                break;
            case R.id.draw_after:
                mContainer.removeAllViews();
                mContainer.addView(mDrawAfterView);
                break;
            case R.id.draw_before:
                mContainer.removeAllViews();
                mContainer.addView(mDrawBeforeView);
                break;
            case R.id.dispatch_draw_before:
                mContainer.removeAllViews();
                mContainer.addView(mDispatchDrawBeforeLinearLayout);
                break;
        }
        return true;
    }


}
