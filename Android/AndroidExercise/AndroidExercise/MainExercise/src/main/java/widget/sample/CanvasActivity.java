package widget.sample;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.nsz.android.R;

import home.BaseAppCompatActivity;
import widget.CheckView;
import widget.RotateCircle;
import widget.RotateClockView;
import widget.ScaleRect;

public class CanvasActivity extends BaseAppCompatActivity {

    private FrameLayout view;
    private ScaleRect scaleRect;
    private RotateCircle rotateCircle;
    private RotateClockView rotateClockView;
    private CheckView checkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new FrameLayout(this);
        scaleRect = new ScaleRect(this);
        view.addView(scaleRect);
        setContentView(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_widget_canvas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        switch (itemId) {
            case R.id.scale:
                scaleRect = new ScaleRect(this);
                view.removeAllViews();
                view.addView(scaleRect);
                break;
            case R.id.rotate:
                rotateCircle = new RotateCircle(this);
                view.removeAllViews();
                view.addView(rotateCircle);
                break;
            case R.id.clock:
                rotateClockView = new RotateClockView(this);
                view.removeAllViews();
                view.addView(rotateClockView);
                break;
            case R.id.bitmap:
                checkView = new CheckView(this);
                view.removeAllViews();
                view.addView(checkView);
                checkView.check();
                break;
        }
        return true;
    }


}
