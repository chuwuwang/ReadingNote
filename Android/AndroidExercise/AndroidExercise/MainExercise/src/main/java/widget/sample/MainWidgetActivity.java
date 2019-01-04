package widget.sample;

import android.os.Bundle;
import android.view.View;

import com.nsz.android.R;

import com.nsz.android.home.BaseAppCompatActivity;

/**
 * 自定义View主界面
 */
public class MainWidgetActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.widget_activity);
        initToolbarBringBack();

        findViewById(R.id.mb_tai_chi).setOnClickListener(this);
        findViewById(R.id.mb_loading_dot).setOnClickListener(this);
        findViewById(R.id.mb_pull_refresh).setOnClickListener(this);
        findViewById(R.id.mb_pie_chat).setOnClickListener(this);
        findViewById(R.id.mb_canvas).setOnClickListener(this);
        findViewById(R.id.mb_path).setOnClickListener(this);
        findViewById(R.id.mb_path_measure).setOnClickListener(this);
        findViewById(R.id.mb_add_number).setOnClickListener(this);
        findViewById(R.id.mb_ele_good_car).setOnClickListener(this);
        findViewById(R.id.mb_wave).setOnClickListener(this);
        findViewById(R.id.mb_adhesion).setOnClickListener(this);
        findViewById(R.id.mb_matrix).setOnClickListener(this);
        findViewById(R.id.mb_matrix_color).setOnClickListener(this);
        findViewById(R.id.mb_matrix_camera).setOnClickListener(this);
        findViewById(R.id.mb_loading_up_down).setOnClickListener(this);
        findViewById(R.id.mb_xiu_xiu).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.mb_tai_chi:
                openActivity(TaiChiActivity.class);
                break;
            case R.id.mb_loading_dot:
                openActivity(BaiDuLoadingActivity.class);
                break;
            case R.id.mb_pull_refresh:
                openActivity(RefreshLVActivity.class);
                break;
            case R.id.mb_pie_chat:
                openActivity(PieChartActivity.class);
                break;
            case R.id.mb_canvas:
                openActivity(CanvasActivity.class);
                break;
            case R.id.mb_path:
                openActivity(PathActivity.class);
                break;
            case R.id.mb_path_measure:
                openActivity(PathMeasureActivity.class);
                break;
            case R.id.mb_add_number:
                openActivity(PropertyAnimatorAddNumberActivity.class);
                break;
            case R.id.mb_ele_good_car:
                openActivity(GoodsCarActivity.class);
                break;
            case R.id.mb_wave:
                openActivity(WaveBezierActivity.class);
                break;
            case R.id.mb_adhesion:
                openActivity(AdhesionActivity.class);
                break;
            case R.id.mb_matrix:
                openActivity(ColorMatrixOperateActivity.class);
                break;
            case R.id.mb_matrix_color:
                openActivity(MatrixOperateActivity.class);
                break;
            case R.id.mb_matrix_camera:
                openActivity(MatrixCameraActivity.class);
                break;
            case R.id.mb_loading_up_down:
                openActivity(UpDownLoadAnimateActivity.class);
                break;
            case R.id.mb_xiu_xiu:
                openActivity(AliXiuXiuViewActivity.class);
                break;
            default:
                break;
        }
    }


}
