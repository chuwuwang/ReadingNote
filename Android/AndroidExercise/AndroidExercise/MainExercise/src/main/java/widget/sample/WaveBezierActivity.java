package widget.sample;

import android.os.Bundle;

import com.nsz.android.R;
import home.BaseAppCompatActivity;
import widget.WaveView;

public class WaveBezierActivity extends BaseAppCompatActivity {

    private WaveView waveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_activity_wave_bezier);
        initToolbarBringBack();
        waveView = (WaveView) findViewById(R.id.wave_view);
        waveView.exeWaveEffect();
    }

}
