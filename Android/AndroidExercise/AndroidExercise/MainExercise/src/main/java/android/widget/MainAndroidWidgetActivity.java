package android.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.nsz.android.R;

import com.nsz.android.home.BaseAppCompatActivity;

/**
 * @author Created by Lee64 on 2017/10/17.
 */

public class MainAndroidWidgetActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.android_activity);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        findViewById(R.id.card_view_view_flipper).setOnClickListener(this);
        findViewById(R.id.card_view_constraint_layout).setOnClickListener(this);
        findViewById(R.id.card_view_chronometer).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.card_view_view_flipper:
                openActivity(ViewFlipperActivity.class);
                break;
            case R.id.card_view_constraint_layout:
                openActivity(ConstraintLayoutActivity.class);
                break;
            case R.id.card_view_chronometer:
                openActivity(ChronometerActivity.class);
                break;
            default:
                break;
        }
    }


}
