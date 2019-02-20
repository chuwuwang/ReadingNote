package android.widget;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.nsz.android.R;

import com.nsz.android.home.BaseAppCompatActivity;

public class ChronometerActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.android_activity_chronometer);
        initToolbarBringBack();
        initView();
    }

    private void initView() {
        Chronometer chronometer = findViewById(R.id.chronometer);
        // 开始
        // chronometer.start();
        // 停止
        // chronometer.stop();
        // 设置初始值（重置）
        // chronometer.setBase(L);
        // 事件监听器，时间发生变化时可进行操作
        chronometer.setOnChronometerTickListener(
                chr -> Log.i("lainey", "时间发生变化")

        );

        // 设置格式(默认"MM:SS"格式)
        chronometer.setFormat("计时：%s");
        chronometer.setText("Chronometer");

        long elapsedRealTime = SystemClock.elapsedRealtime();
        // setBase 设置基准时间 即表示从当前时间开始重新计时）。
        chronometer.setBase(elapsedRealTime);

        chronometer.start();
    }


}
