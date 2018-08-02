package material.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.nsz.android.R;

import home.BaseAppCompatActivity;

public class MainMaterialDesignActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.md_activity_main);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        findViewById(R.id.tv_dialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.tv_dialog:
                openActivity(DialogsActivity.class);
                break;
            default:
                break;
        }
    }


}
