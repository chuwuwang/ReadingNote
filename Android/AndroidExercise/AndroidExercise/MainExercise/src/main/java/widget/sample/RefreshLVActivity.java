package widget.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nsz.android.R;

import java.util.ArrayList;
import java.util.List;

import com.nsz.android.home.BaseAppCompatActivity;
import widget.PullToRefreshListView;

public class RefreshLVActivity extends BaseAppCompatActivity {

    private List<String> mList;
    private MyAdapter mAdapter;
    private PullToRefreshListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.widget_activity_pull_to_refresh_list_view);
        initToolbarBringBack();

        mListView = findViewById(R.id.refresh_list_view);

        mList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            mList.add("geek lee hello world ! " + i);
        }
        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(
                (parent, view, position, id) -> {
                    String str = mList.get(position);
                    Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                }
        );
        mListView.setOnRefreshListener(
                new PullToRefreshListView.OnRefreshListener() {

                    @Override
                    public void onPullToRefresh() {
                        new Handler().postDelayed(
                                () -> {
                                    mListView.refreshComplete();
                                    mList.add("yes 是最帅的~");
                                    mAdapter.notifyDataSetChanged();
                                }, 2000);
                    }

                    @Override
                    public void onLoadMore() {
                        new Handler().postDelayed(
                                () -> {
                                    mListView.refreshComplete();
                                    mList.add("yes 没人比你更帅了~");
                                    mAdapter.notifyDataSetChanged();
                                }, 2000);
                    }

                }
        );

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final String str = mList.get(position);
            TextView view = new TextView(RefreshLVActivity.this);
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 130);
            view.setLayoutParams(params);
            view.setGravity(Gravity.CENTER);
            view.setText(str);
            view.setTextColor(Color.BLUE);
            view.setTextSize(16.0f);
            return view;
        }

    }


}
