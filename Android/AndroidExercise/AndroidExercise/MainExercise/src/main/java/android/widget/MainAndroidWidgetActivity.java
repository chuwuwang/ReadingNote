package android.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.nsz.android.R;

import home.BaseAppCompatActivity;

/**
 * @author Created by Lee64 on 2017/10/17.
 */

public class MainAndroidWidgetActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_activity);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        findViewById(R.id.tv_view_flipper).setOnClickListener(this);
        findViewById(R.id.tv_constraint_layout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.tv_view_flipper:
                openActivity(ViewFlipperActivity.class);
                break;
            case R.id.tv_constraint_layout:
                openActivity(ConstraintLayoutActivity.class);
                break;
            default:
                break;
        }
    }

}
