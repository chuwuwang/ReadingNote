package widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nsz.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 百度加载圈
 *
 * @author Leeshenzhou
 */
public class BaiDuLoading extends FrameLayout {

    // 存放三个ImageView的的集合
    private List<ImageView> views;

    // 让左边和右边动画同时执行的AnimatorSet对象
    private AnimatorSet animatorSet;

    // 动画所执行的最大半径(即中间点和最左边的距离)
    private int maxRadius = 80;

    // 开始执行的第一个动画的索引， 由于第一个和第二个同时当执行， 当第一遍执行完毕后就让第一个停下来在中间位置，换原来中间位置的第三个开始执行动画.
    // 以此类推，当第二遍执行完毕后第二个停下来，中间位置的开始执行动画.
    private int startIndex = 0;

    // 交换执行动画的源图片数组
    private int[] src;

    public BaiDuLoading(Context context) {
        super(context);
        init();
    }

    public BaiDuLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaiDuLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_activity_baidu_loading, this, true);

        ImageView iv_blue = (ImageView) findViewById(R.id.iv_blue);
        ImageView iv_yellow = (ImageView) findViewById(R.id.iv_yellow);
        ImageView iv_red = (ImageView) findViewById(R.id.iv_red);

        src = new int[]{R.drawable.ic_dot_yellow, R.drawable.ic_dot_red, R.drawable.ic_dot_blue};
        views = new ArrayList<>();
        views.add(iv_yellow);
        views.add(iv_red);
        views.add(iv_blue);

        startAnimator();
    }

    private void startAnimator() {
        // 向左来回移动的X位移动画
        ObjectAnimator objectAnimatorLeft = ObjectAnimator.ofFloat(views.get(0), "translationX", 0, -maxRadius, 0);
        objectAnimatorLeft.setRepeatCount(-1);
        objectAnimatorLeft.setDuration(800);

        // 向右来回移动的X位移动画
        ObjectAnimator objectAnimatorRight = ObjectAnimator.ofFloat(views.get(1), "translationX", 0, maxRadius, 0);
        objectAnimatorRight.setRepeatCount(-1);
        objectAnimatorRight.setDuration(800);

        // 动画组合.让左右同时执行
        animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimatorRight).with(objectAnimatorLeft);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.start();

        objectAnimatorLeft.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationRepeat(Animator animation) {
                // 每次记录一下下次应该停止在中间的Image索引，然后和中间的交换
                if (startIndex == 0) {
                    sweep(0, 2);
                    startIndex = 1;
                } else {
                    sweep(1, 2);
                    startIndex = 0;
                }
            }

        });

    }

    /**
     * 每次让先执行动画的目标和中间停止的动画目标交换
     */
    private void sweep(int a, int b) {
        views.get(a).setImageResource(src[b]);
        views.get(b).setImageResource(src[a]);
        int temp = src[b];
        src[b] = src[a];
        src[a] = temp;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animatorSet.cancel();
    }

}
