package google;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.nsz.android.R;

import com.nsz.android.home.BaseAppCompatActivity;

/**
 * @author Created by Lee64 on 2018/1/24.
 */

public class MainGoogleActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_activity);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        findViewById(R.id.cv_network_connect).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.cv_network_connect:
                openActivity(NetworkConnectActivity.class);
                break;
            default:
                break;
        }
    }


}
