package widget.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;

import home.BaseAppCompatActivity;
import com.nsz.android.R;
import widget.UpDownLoadView;

/**
 * 上抛和下落的加载动画
 *
 * @author Created by Lee64 on 2017/10/10.
 */

public class UpDownLoadAnimateActivity extends BaseAppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_activity_up_down_load);
        initToolbarBringBack();
        UpDownLoadView loadView = (UpDownLoadView) findViewById(R.id.load_view);
        loadView.startLoading();

    }


}
