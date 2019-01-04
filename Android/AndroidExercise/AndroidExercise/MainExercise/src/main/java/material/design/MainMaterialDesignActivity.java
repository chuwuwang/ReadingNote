package material.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.nsz.android.R;

import com.nsz.android.home.BaseAppCompatActivity;

/*

    Android Material Design

 */
public class MainMaterialDesignActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.md_activity_main);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        findViewById(R.id.cv_dialog).setOnClickListener(this);
        findViewById(R.id.cv_material_button).setOnClickListener(this);
        findViewById(R.id.cv_chip_group).setOnClickListener(this);
        findViewById(R.id.cv_bab).setOnClickListener(this);
        findViewById(R.id.cv_til).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.cv_dialog:
                openActivity(DialogsActivity.class);
                break;
            case R.id.cv_material_button:
                openActivity(MaterialButtonActivity.class);
                break;
            case R.id.cv_chip_group:
                openActivity(ChipGroupAndChipActivity.class);
                break;
            case R.id.cv_bab:
                openActivity(BottomAppBarActivity.class);
                break;
            case R.id.cv_til:
                openActivity(TextInputLayoutActivity.class);
                break;
            default:
                break;
        }
    }


}
