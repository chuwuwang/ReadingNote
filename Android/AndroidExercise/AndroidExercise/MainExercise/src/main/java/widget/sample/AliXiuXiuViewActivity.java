package widget.sample;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.animation.AccelerateInterpolator;

import com.nsz.android.R;

import com.nsz.android.home.BaseAppCompatActivity;
import widget.AliXiuXiuView;

public class AliXiuXiuViewActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.widget_activity_ali_xiu);
        initToolbarBringBack();

        int color = Color.parseColor("#ff0000");
        AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator(1.2f);
        LinearOutSlowInInterpolator linearOutSlowInInterpolator = new LinearOutSlowInInterpolator();

        AliXiuXiuView aliXiuXiuView = findViewById(R.id.xiu_view_1);
        aliXiuXiuView.setDuration(5000);
        aliXiuXiuView.setStyle(Paint.Style.STROKE);
        aliXiuXiuView.setSpeed(400);
        aliXiuXiuView.setColor(color);
        aliXiuXiuView.setInterpolator(accelerateInterpolator);
        aliXiuXiuView.start();

        aliXiuXiuView = findViewById(R.id.xiu_view_2);
        aliXiuXiuView.start();

        aliXiuXiuView = findViewById(R.id.xiu_view_3);
        aliXiuXiuView.setDuration(5000);
        aliXiuXiuView.setStyle(Paint.Style.FILL);
        aliXiuXiuView.setColor(color);
        aliXiuXiuView.setInterpolator(linearOutSlowInInterpolator);
        aliXiuXiuView.start();

    }

}
