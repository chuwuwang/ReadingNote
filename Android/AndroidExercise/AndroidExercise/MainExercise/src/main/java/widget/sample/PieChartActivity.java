package widget.sample;

import android.os.Bundle;

import home.BaseAppCompatActivity;
import com.nsz.android.R;
import widget.PieView;
import widget.PieView.Pie;

import java.util.ArrayList;

public class PieChartActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_activity_pie_chart);

        initToolbarBringBack();

        PieView view = (PieView) findViewById(R.id.pie_view);

        ArrayList<Pie> list = new ArrayList<>();

        Pie pieData1 = new Pie("lee", 60);
        Pie pieData2 = new Pie("ka", 30);
        Pie pieData3 = new Pie("yes", 40);
        Pie pieData4 = new Pie("sloop", 20);
        Pie pieData5 = new Pie("mustang", 20);
        Pie pieData6 = new Pie("geek", 10);
        Pie pieData7 = new Pie("ling", 5);

        list.add(pieData1);
        list.add(pieData2);
        list.add(pieData3);
        list.add(pieData4);
        list.add(pieData5);
        list.add(pieData6);
        list.add(pieData7);

        view.setData(list);
    }


}
