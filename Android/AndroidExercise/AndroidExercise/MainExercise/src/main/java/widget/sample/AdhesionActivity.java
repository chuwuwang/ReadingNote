package widget.sample;


import android.os.Bundle;

import home.BaseAppCompatActivity;
import com.nsz.android.R;

public class AdhesionActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_activity_adhesion);
        initToolbarBringBack();
    }


}
