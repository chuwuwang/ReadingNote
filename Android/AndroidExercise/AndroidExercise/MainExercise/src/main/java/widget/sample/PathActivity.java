package widget.sample;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.nsz.android.R;

import home.BaseAppCompatActivity;
import widget.CubicBezierView;
import widget.HeartBezierView;
import widget.QuadBezierView;
import widget.RadarView;

/**
 * Path操作
 */
public class PathActivity extends BaseAppCompatActivity {

    private FrameLayout view;

    private RadarView radarView;
    private QuadBezierView quadBezierView;
    private CubicBezierView cubicBezierView;
    private HeartBezierView heartBezierView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new FrameLayout(this);
        radarView = new RadarView(this);
        quadBezierView = new QuadBezierView(this);
        cubicBezierView = new CubicBezierView(this);
        view.addView(radarView);
        setContentView(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_widget_path, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.radar:
                view.removeAllViews();
                view.addView(radarView);
                break;
            case R.id.quad:
                view.removeAllViews();
                view.addView(quadBezierView);
                break;
            case R.id.cubic:
                boolean mode = cubicBezierView.isMode();
                if (mode) {
                    cubicBezierView.setMode(false);
                } else {
                    cubicBezierView.setMode(true);
                }
                view.removeAllViews();
                view.addView(cubicBezierView);
                break;
            case R.id.heart:
                heartBezierView = new HeartBezierView(this);
                view.removeAllViews();
                view.addView(heartBezierView);
                break;
            default:
                break;
        }
        return false;
    }

}
