package android.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nsz.android.R;

import com.nsz.android.home.BaseAppCompatActivity;

public class ConstraintLayoutActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.android_activity_constranint_layout);
        initToolbarBringBack();
    }

}
