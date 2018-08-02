package widget.sample;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nsz.android.R;

import home.BaseAppCompatActivity;
import widget.MatrixSetPolyToPoly;
import widget.MatrixSetRectToRect;

public class MatrixOperateActivity extends BaseAppCompatActivity {

    MatrixSetPolyToPoly polyMatrixView;
    MatrixSetRectToRect rectMatrixView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_activity_matrix_polygon);
        polyMatrixView = (MatrixSetPolyToPoly) findViewById(R.id.view_set_polygon);
        rectMatrixView = (MatrixSetRectToRect) findViewById(R.id.view_set_rect);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_widget_matrix_polygon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.polygon:
                polyMatrixView.setVisibility(View.VISIBLE);
                rectMatrixView.setVisibility(View.GONE);
                break;
            case R.id.point:

                break;
            case R.id.center:
                polyMatrixView.setVisibility(View.GONE);
                rectMatrixView.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        return false;
    }

}
