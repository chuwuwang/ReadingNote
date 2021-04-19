package hen.coder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.nsz.android.R;

import com.nsz.android.home.BaseAppCompatActivity;

public class HenCoderMainActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hen_coder_activity_main);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        findViewById(R.id.btn_draw_order).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.btn_draw_order:
                openActivity(HenCoderDrawOrderActivity.class);
                break;
            default:
                break;
        }
    }


}
