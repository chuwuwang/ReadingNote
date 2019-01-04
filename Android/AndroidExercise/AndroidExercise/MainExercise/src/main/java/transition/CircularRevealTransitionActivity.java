package transition;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.nsz.android.R;
import com.nsz.android.home.BaseAppCompatActivity;

/**
 * Circular Reveal 官方将这一动画称为揭露效果，它在官网中的描述是这样的：
 * 当您显示或隐藏一组 UI 元素时，揭露动画可为用户提供视觉连续性。ViewAnimationUtils.createCircularReveal()
 * 方法让您能够为裁剪区域添加动画以揭露或隐藏视图。
 *
 * @author Created by Lee64 on 2017/9/28.
 */

public class CircularRevealTransitionActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_activity_circular_reveal);
        initView();
        initToolbarBringBack();
    }

    /*
    Animator createCircularReveal (View view,                   // 将要变化的 View
                                   int centerX,                 // 动画圆的中心的x坐标
                                   int centerY,                 // 动画圆的中心的y坐标
                                   float startRadius,           // 动画圆的起始半径
                                   float endRadius              // 动画圆的结束半径
    )
    */

    private void initView() {
        final View touch = findViewById(R.id.touch_me_view);
        touch.post(new Runnable() {

            @Override
            public void run() {
                float radius = (float) Math.hypot(touch.getHeight(), touch.getWidth());
                Animator animator = ViewAnimationUtils.createCircularReveal(touch, 0, 0, 0, radius);
                animator.setDuration(800).start();
            }

        });
        touch.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, final MotionEvent event) {
                final int x = (int) event.getX();
                final int y = (int) event.getY();
                Intent i = new Intent(CircularRevealTransitionActivity.this, CircularRevealTransitionEnterActivity.class);
                i.putExtra("touch_x", x);
                i.putExtra("touch_y", y);
                startActivity(i);
                return false;
            }

        });
    }

}
