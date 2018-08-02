package widget.sample;

import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nsz.android.R;

import java.util.ArrayList;
import java.util.List;

import home.BaseAppCompatActivity;
import widget.MoveAddBezierView;

public class GoodsCarActivity extends BaseAppCompatActivity {

    private TextView tvGoodsNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_activity_goods);

        initToolbarBringBack();

        ListView listView = (ListView) findViewById(R.id.lv_main);
        tvGoodsNumber = (TextView) findViewById(R.id.tv_good_fitting_num);

        List<GoodModel> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            GoodModel model = new GoodModel();
            model.name = "葱花鸡肉卷";
            model.description = "炸酱、番茄、面条";
            list.add(model);
        }

        GoodAdapter adapter = new GoodAdapter(this, list,
                v -> {
                    int[] startPos = new int[2];
                    v.getLocationInWindow(startPos);

                    MoveAddBezierView bezierView = new MoveAddBezierView(GoodsCarActivity.this);
                    bezierView.setStartPosition(new PointF(startPos[0], startPos[1]));
                    ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
                    viewGroup.addView(bezierView);

                    int endPos[] = new int[2];
                    tvGoodsNumber.getLocationInWindow(endPos);
                    bezierView.setEndPosition(new PointF(endPos[0], endPos[1]));
                    bezierView.startMoveAddAnimation();
                }
        );
        listView.setAdapter(adapter);
    }

    private class GoodAdapter extends BaseAdapter implements OnClickListener {

        private Context context;
        private List<GoodModel> models;
        private GoodActionListener listener;

        public GoodAdapter(Context context, List<GoodModel> models, GoodActionListener listener) {
            super();
            this.context = context;
            this.models = models;
            this.listener = listener;
        }

        @Override
        public int getCount() {
            return models.size();
        }

        @Override
        public Object getItem(int position) {
            return models.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(context, R.layout.widget_item_goods_car, null);
                holder.tvName = (TextView) convertView.findViewById(R.id.tv_goods_fits_name);
                holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_goods_fits_price);
                holder.imgAdd = (ImageView) convertView.findViewById(R.id.iv_goods_fits_add);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            GoodModel model = models.get(position);
            holder.tvName.setText(model.name);
            holder.tvPrice.setText(model.description);
            holder.imgAdd.setOnClickListener(this);
            return convertView;
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.addAnimationView(v);
            }
        }

    }

    private static class ViewHolder {

        TextView tvName;
        TextView tvPrice;
        ImageView imgAdd;

    }

    private class GoodModel {

        public String name;
        public String description;

    }

    private interface GoodActionListener {

        void addAnimationView(View v);

    }

}
