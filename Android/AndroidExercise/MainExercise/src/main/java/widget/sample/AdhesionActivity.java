package widget.sample;


import android.os.Bundle;

import com.nsz.android.home.BaseAppCompatActivity;
import com.nsz.android.R;

public class AdhesionActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.widget_activity_adhesion);
        initToolbarBringBack();
    }


}
