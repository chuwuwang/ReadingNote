package transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.nsz.android.home.BaseAppCompatActivity;
import com.nsz.android.R;

/**
 * @author Created by Lee64 on 2017/9/28.
 */

public class CircularRevealTransitionEnterActivity extends BaseAppCompatActivity {

    private View content;
    private int x;
    private int y;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_activity_circular_reveal_enter);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        content = findViewById(R.id.view_content);
        content.post(new Runnable() {

            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    x = getIntent().getIntExtra("touch_x", 0);
                    y = getIntent().getIntExtra("touch_y", 0);
                    Animator animator = createRevealAnimator(content, false, x, y);
                    animator.start();
                }
            }

        });
    }

    private Animator createRevealAnimator(final View view, boolean reversed, int x, int y) {
        float radius = (float) Math.hypot(view.getHeight(), view.getWidth());
        float startRadius = reversed ? radius : 0;
        float endRadius = reversed ? 0 : radius;

        Animator animator = ViewAnimationUtils.createCircularReveal(view, x, y, startRadius, endRadius);
        animator.setDuration(800);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        if (reversed) {
            animator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.INVISIBLE);
                    finish();
                }

            });
        }
        return animator;
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = createRevealAnimator(content, true, x, y);
            animator.start();
        } else {
            super.onBackPressed();
        }
    }

}
