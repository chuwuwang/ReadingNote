package home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MainAndroidWidgetActivity;
import android.widget.TextView;

import com.nsz.android.R;

import java.util.ArrayList;
import java.util.List;

import google.MainGoogleActivity;
import paint.MainHenCoderPaintActivity;
import transition.MainTransitionActivity;
import widget.sample.MainWidgetActivity;


/**
 * @author Created by Lee64 on 2017/10/3.
 */

public class MainActivity extends BaseAppCompatActivity {

    private static final String TAG = "MainActivity";

    private ListView drawerListView;
    private DrawerLayout drawerLayout;
    private DrawerAdapter drawerAdapter;
    private List<Drawer> list;

    private ImageView ivMenu;
    private ImageView ivSearch;

    private ImageView ivIndex;
    private TextView tvIndex;
    private ImageView ivNews;
    private TextView tvNews;
    private ImageView ivAsset;
    private TextView tvAsset;
    private ImageView ivPerson;
    private TextView tvPerson;

    private int normalColor;
    private int selectColor;
    // 记录当前是哪个fragment
    public int mark = 0;
    public Fragment[] fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initDrawerData();
        initView();
        initFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        drawerAdapter.selectItem(-1);
    }

    private void initView() {
        normalColor = Color.parseColor("#969696");
        selectColor = Color.parseColor("#3F51B5");

        drawerListView = (ListView) findViewById(R.id.drawer_list_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ivMenu = (ImageView) findViewById(R.id.iv_back);
        ivSearch = (ImageView) findViewById(R.id.iv_search);
        ivMenu.setOnClickListener(this);
        ivSearch.setOnClickListener(this);

        View index = findViewById(R.id.index);
        ivIndex = (ImageView) findViewById(R.id.iv_index);
        tvIndex = (TextView) findViewById(R.id.tv_index);
        index.setOnClickListener(this);

        View news = findViewById(R.id.news);
        ivNews = (ImageView) findViewById(R.id.iv_news);
        tvNews = (TextView) findViewById(R.id.tv_news);
        news.setOnClickListener(this);

        View asset = findViewById(R.id.asset);
        ivAsset = (ImageView) findViewById(R.id.iv_asset);
        tvAsset = (TextView) findViewById(R.id.tv_asset);
        asset.setOnClickListener(this);

        View person = findViewById(R.id.person);
        ivPerson = (ImageView) findViewById(R.id.iv_person);
        tvPerson = (TextView) findViewById(R.id.tv_person);
        person.setOnClickListener(this);


        drawerAdapter = new DrawerAdapter(this, list);
        View header = View.inflate(this, R.layout.main_item_drawer_header, null);
        drawerListView.addHeaderView(header);
        drawerListView.setAdapter(drawerAdapter);
        drawerListView.setOnItemClickListener(
                (parent, view, position, id) -> {
                    position = position - 1;
                    Drawer drawer = list.get(position);
                    Log.d(TAG, "position:" + position + " name:" + drawer.name);
                    clickDrawer(position);
                    drawerAdapter.selectItem(position);
                    drawerLayout.closeDrawers();
                }
        );
    }

    private void clickDrawer(int position) {
        switch (position) {
            case 0:
                openActivity(MainWidgetActivity.class);
                break;
            case 1:
                openActivity(MainGoogleActivity.class);
                break;
            case 2:
                openActivity(MainAndroidWidgetActivity.class);
                break;
            case 3:
                openActivity(MainTransitionActivity.class);
                break;
            case 4:
                openActivity(MainHenCoderPaintActivity.class);
                break;
        }
    }

    private void initDrawerData() {
        list = new ArrayList<>();
        Drawer drawer = new Drawer("View 绘制", 0);
        list.add(drawer);
        drawer = new Drawer("Google 官方示例", 0);
        list.add(drawer);
        drawer = new Drawer("Android 官方控件", 0);
        list.add(drawer);
        drawer = new Drawer("Transition 过渡动画", 0);
        list.add(drawer);
        drawer = new Drawer("HenCoder Paint 详解", 0);
        list.add(drawer);
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragments = new Fragment[]{
                IndexFragment.newInstance(),
                NewsFragment.newInstance(),
                AssetFragment.newInstance(),
                PersonFragment.newInstance()
        };
        transaction.add(R.id.container, fragments[0]);
        transaction.show(fragments[0]);
        transaction.commitAllowingStateLoss();
        resetTab();
        tvIndex.setTextColor(selectColor);
        ivIndex.setImageResource(R.drawable.home_index_press);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.iv_back:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.iv_search:
                break;
            case R.id.index:
                resetTab();
                ivIndex.setImageResource(R.drawable.home_index_press);
                tvIndex.setTextColor(selectColor);
                showFragment(0);
                break;
            case R.id.news:
                resetTab();
                ivNews.setImageResource(R.drawable.home_news_press);
                tvNews.setTextColor(selectColor);
                showFragment(1);
                break;
            case R.id.asset:
                resetTab();
                ivAsset.setImageResource(R.drawable.home_asset_press);
                tvAsset.setTextColor(selectColor);
                showFragment(2);
                break;
            case R.id.person:
                resetTab();
                ivPerson.setImageResource(R.drawable.home_person_press);
                tvPerson.setTextColor(selectColor);
                showFragment(3);
                break;
        }
    }

    private void showFragment(int position) {
        if (position == mark) return;
        // hide
        hideAllFragment();
        // add
        Fragment showFragment = fragments[position];
        addFragment(showFragment);
        // show
        showFragment(showFragment);
        mark = position;
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    private void addFragment(Fragment fragment) {
        if (fragment.isAdded()) return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();
    }

    private void hideAllFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : fragments) {
            boolean hidden = fragment.isHidden();
            if (hidden) {
                continue;
            }
            transaction.hide(fragment);
        }
        transaction.commit();
    }

    private void resetTab() {
        ivIndex.setImageResource(R.drawable.home_index_normal);
        ivNews.setImageResource(R.drawable.home_news_normal);
        ivAsset.setImageResource(R.drawable.home_asset_normal);
        ivPerson.setImageResource(R.drawable.home_person_normal);
        tvIndex.setTextColor(normalColor);
        tvNews.setTextColor(normalColor);
        tvAsset.setTextColor(normalColor);
        tvPerson.setTextColor(normalColor);
    }


}
