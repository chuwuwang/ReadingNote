package transition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.nsz.android.R;

import com.nsz.android.home.BaseAppCompatActivity;

/**
 * Transition Framework 过渡动画主界面
 *
 * @author Created by Lee64 on 2017/9/26.
 */

public class MainTransitionActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_activity);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        findViewById(R.id.tv_basic_transition).setOnClickListener(this);
        findViewById(R.id.tv_custom_transition).setOnClickListener(this);
        findViewById(R.id.tv_shared_element_transition).setOnClickListener(this);
        findViewById(R.id.tv_circular_reveal_transition).setOnClickListener(this);
        findViewById(R.id.tv_circular_reveal_fragment).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_basic_transition:
                openActivity(BasicTransitionActivity.class);
                break;
            case R.id.tv_custom_transition:
                openActivity(CustomTransitionActivity.class);
                break;
            case R.id.tv_shared_element_transition:
                openActivity(SharedElementTransitionActivity.class);
                break;
            case R.id.tv_circular_reveal_transition:
                openActivity(CircularRevealTransitionActivity.class);
                break;
            case R.id.tv_circular_reveal_fragment:
                openActivity(CircularRevealFragmentActivity.class);
                break;
        }
    }

}
