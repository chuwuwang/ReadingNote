package widget.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.nsz.android.R;
import home.BaseAppCompatActivity;
import widget.TaiChiView;

public class TaiChiActivity extends BaseAppCompatActivity {

    TaiChiView taiChiView;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_activity_taichi);

        initToolbarBringBack();

        taiChiView = (TaiChiView) findViewById(R.id.TaiChiView);

        Handler handler = new Handler() {

            private float degrees = 0;

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                taiChiView.setRotate(degrees += 5);
                this.sendEmptyMessageDelayed(0, 80);
            }
        };

        handler.sendEmptyMessageDelayed(0, 20);

    }

}
