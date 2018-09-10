package widget.sample;

import android.os.Bundle;

import home.BaseAppCompatActivity;
import com.nsz.android.R;

public class BaiDuLoadingActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.widget_activity_bd_loading);
        initToolbarBringBack();
    }

}
