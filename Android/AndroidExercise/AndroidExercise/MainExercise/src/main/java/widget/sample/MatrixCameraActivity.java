package widget.sample;


import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.nsz.android.R;

import home.BaseAppCompatActivity;
import widget.Rotate3dAnimation;

public class MatrixCameraActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_activity_matrix_camer);

        initToolbarBringBack();

        ImageView view = (ImageView) findViewById(R.id.image_view);
        view.setImageResource(R.drawable.quadratic_element);
        view.setOnClickListener(
                v -> {
                    // 计算中心点（这里是使用view的中心作为旋转的中心点）
                    final float centerX = v.getWidth() / 2.0f;
                    final float centerY = v.getHeight() / 2.0f;

                    // 括号内参数分别为（上下文，开始角度，结束角度，x轴中心点，y轴中心点，深度，是否扭曲）
                    final Rotate3dAnimation rotation = new Rotate3dAnimation(MatrixCameraActivity.this, 0, 180, centerX, centerY, 0f, true);

                    rotation.setDuration(3000);                         // 设置动画时长
                    rotation.setFillAfter(true);                        // 保持旋转后效果
                    // 设置插值器
                    LinearInterpolator linearInterpolator = new LinearInterpolator();
                    rotation.setInterpolator(linearInterpolator);
                    v.startAnimation(rotation);

                }
        );
    }


}
