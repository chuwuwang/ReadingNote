package android.widget;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.nsz.android.R;

/**
 * ViewFlipper的使用
 *
 * @author Created by Lee64 on 2017/10/17.
 */

public class ViewFlipperActivity extends AppCompatActivity {

    private static final String TAG = "ViewFlipperActivity";

    private ViewFlipper viewFlipper;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_activity_view_flipper);
        initView();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        viewFlipper = findViewById(R.id.view_flipper);
        viewFlipper.setOnTouchListener(
                (v, event) -> gestureDetector.onTouchEvent(event)
        );
        SimpleGestureListener simpleGestureListener = new SimpleGestureListener();
        gestureDetector = new GestureDetector(simpleGestureListener);

        viewFlipper.startFlipping();
    }

    private class SimpleGestureListener extends GestureDetector.SimpleOnGestureListener {

        final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(TAG, "onDown:" + e.getAction());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(TAG, "velocityX:" + velocityX + " velocityY:" + velocityY);
            // Fling left
            if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                viewFlipper.showNext();
            } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // Fling right
                viewFlipper.showPrevious();
            }
            return true;
        }

    }


}
