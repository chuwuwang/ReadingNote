package transition;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nsz.android.R;
import home.BaseAppCompatActivity;

public class FruitTransitionActivity extends BaseAppCompatActivity {

    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";

    private View fruitBgView;
    private ImageView fruitImageView;

    private String fruitName;
    private int fruitImageId;

    // Slide 滑动切换可以使活动从屏幕右侧或底部滑入/出。可能你以前有过类似的效果，但是这个新切换更加灵活。
    // android:slideEdge="end"
    // 设置切换的slideEdge为end（右侧），从而实现从右侧开始滑动——若想要底部滑动将设置为 bottom。

    // Fade 渐变切换使活动转换出现淡入或淡出的效果。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_activity_fruit);
        getIntentBundle();
        initToolbarBringBack(fruitName);
        initView();
        initAnimation();
    }

    private void getIntentBundle() {
        Intent intent = getIntent();
        fruitName = intent.getStringExtra(FRUIT_NAME);
        fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);
    }

    private void initView() {
        fruitBgView = findViewById(R.id.fruit_bg);
        fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        TextView fruitContentText = (TextView) findViewById(R.id.fruit_content_text);
        String fruitContent = generateFruitContent(fruitName);
        fruitContentText.setText(fruitContent);
        Glide.with(this).load(fruitImageId).into(fruitImageView);
    }

    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    private void initAnimation() {
        Window window = getWindow();
        // 首次进入显示的动画
        Transition transition = initContentEnterTransition();
        window.setEnterTransition(transition);
        // 指示共享元素首次进入显示的动画
        transition = initSharedElementEnterTransition();
        window.setSharedElementEnterTransition(transition);
        // 调用 finishAfterTransition() 退出时，此页面退出的动画
        transition = TransitionInflater.from(this).inflateTransition(R.transition.fruit_return_slide);
        window.setReturnTransition(transition);
    }

    private Transition initSharedElementEnterTransition() {
        final Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fruit_shared_element);
        transition.addListener(new Transition.TransitionListener() {

            @Override
            public void onTransitionStart(Transition transition) {
                // Circular Reveal(循环显示) 官方将这一动画称为揭露效果
                Animator animator = ViewAnimationUtils.createCircularReveal(fruitBgView, fruitBgView.getWidth() / 2, fruitBgView.getHeight() / 2,
                        fruitImageView.getWidth() / 2, Math.max(fruitBgView.getWidth(), fruitBgView.getHeight()));
                fruitBgView.setBackgroundColor(Color.BLACK);
                animator.setDuration(600);
                animator.start();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }

        });
        return transition;
    }

    private Transition initContentEnterTransition() {
        final Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fruit_slide_and_fade);
        transition.addListener(new Transition.TransitionListener() {

            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }

        });
        return transition;
    }


}