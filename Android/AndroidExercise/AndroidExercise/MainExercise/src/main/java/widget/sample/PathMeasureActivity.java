package widget.sample;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.nsz.android.R;

import java.util.Timer;
import java.util.TimerTask;

import home.BaseAppCompatActivity;
import widget.ArrowPathMeasureView;
import widget.LoveBezierLayout;

public class PathMeasureActivity extends BaseAppCompatActivity {

    private FrameLayout view;
    private LoveBezierLayout loveBezierLayout;
    private ArrowPathMeasureView arrowPathMeasureView;

    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new FrameLayout(this);
        setContentView(view);
        arrowPathMeasureView = new ArrowPathMeasureView(this);
        view.addView(arrowPathMeasureView);
        loveBezierLayout = new LoveBezierLayout(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_widget_path_measure, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        switch (itemId) {
            case R.id.arrow:
                view.removeAllViews();
                view.addView(arrowPathMeasureView);
                timer.cancel();
                break;
            case R.id.love:
                view.removeAllViews();
                view.addView(loveBezierLayout);
                MyTask task = new MyTask();
                timer.scheduleAtFixedRate(task, 350, 350);
                break;
            default:
                break;
        }
        return true;
    }

    private class MyTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(
                    () -> loveBezierLayout.addLoveDrawable()
            );
        }

    }


}
