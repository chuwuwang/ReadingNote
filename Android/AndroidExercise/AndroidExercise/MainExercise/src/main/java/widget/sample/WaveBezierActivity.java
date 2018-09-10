package widget.sample;

import android.os.Bundle;

import com.nsz.android.R;

import home.BaseAppCompatActivity;
import widget.WaveView;

public class WaveBezierActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.widget_activity_wave_bezier);
        initToolbarBringBack();

        WaveView waveView = findViewById(R.id.wave_view);
        waveView.exeWaveEffect();
    }

}
