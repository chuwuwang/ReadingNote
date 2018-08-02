package widget.sample;

import android.os.Bundle;
import android.view.View;

import com.nsz.android.R;

import home.BaseAppCompatActivity;

/**
 * 自定义View主界面
 */
public class MainWidgetActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_activity);
        initToolbarBringBack();

        findViewById(R.id.tv_tai_chi).setOnClickListener(this);
        findViewById(R.id.tv_bai_loading).setOnClickListener(this);
        findViewById(R.id.tv_pull_refresh).setOnClickListener(this);
        findViewById(R.id.tv_pie_chat).setOnClickListener(this);
        findViewById(R.id.tv_start_canvas).setOnClickListener(this);
        findViewById(R.id.tv_operate_path).setOnClickListener(this);
        findViewById(R.id.tv_operate_path_measure).setOnClickListener(this);
        findViewById(R.id.tv_property_animation_add).setOnClickListener(this);
        findViewById(R.id.tv_e_good_car).setOnClickListener(this);
        findViewById(R.id.tv_wave_bezier).setOnClickListener(this);
        findViewById(R.id.tv_operate_adhesion).setOnClickListener(this);
        findViewById(R.id.tv_operate_color_matrix).setOnClickListener(this);
        findViewById(R.id.tv_operate_matrix).setOnClickListener(this);
        findViewById(R.id.tv_operate_matrix_camera).setOnClickListener(this);
        findViewById(R.id.tv_widget_up_down_load).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.tv_tai_chi:
                openActivity(TaiChiActivity.class);
                break;
            case R.id.tv_bai_loading:
                openActivity(BaiDuLoadingActivity.class);
                break;
            case R.id.tv_pull_refresh:
                openActivity(RefreshLVActivity.class);
                break;
            case R.id.tv_pie_chat:
                openActivity(PieChartActivity.class);
                break;
            case R.id.tv_start_canvas:
                openActivity(CanvasActivity.class);
                break;
            case R.id.tv_operate_path:
                openActivity(PathActivity.class);
                break;
            case R.id.tv_operate_path_measure:
                openActivity(PathMeasureActivity.class);
                break;
            case R.id.tv_property_animation_add:
                openActivity(PropertyAnimatorAddNumberActivity.class);
                break;
            case R.id.tv_e_good_car:
                openActivity(GoodsCarActivity.class);
                break;
            case R.id.tv_wave_bezier:
                openActivity(WaveBezierActivity.class);
                break;
            case R.id.tv_operate_adhesion:
                openActivity(AdhesionActivity.class);
                break;
            case R.id.tv_operate_color_matrix:
                openActivity(ColorMatrixOperateActivity.class);
                break;
            case R.id.tv_operate_matrix:
                openActivity(MatrixOperateActivity.class);
                break;
            case R.id.tv_operate_matrix_camera:
                openActivity(MatrixCameraActivity.class);
                break;
            case R.id.tv_widget_up_down_load:
                openActivity(UpDownLoadAnimateActivity.class);
                break;
            default:
                break;
        }
    }


}
