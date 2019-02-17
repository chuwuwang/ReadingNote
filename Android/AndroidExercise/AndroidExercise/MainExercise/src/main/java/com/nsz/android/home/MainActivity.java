package com.nsz.android.home;

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

import com.jet.pack.JetPackActivity;
import com.nsz.android.R;

import java.util.ArrayList;
import java.util.List;

import google.MainGoogleActivity;
import hen.coder.HenCoderMainActivity;
import material.design.MainMaterialDesignActivity;
import transition.MainTransitionActivity;
import widget.sample.MainWidgetActivity;

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
        setImmerseStatus();
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

        drawerListView = findViewById(R.id.drawer_list_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        ivMenu = findViewById(R.id.iv_back);
        ivSearch = findViewById(R.id.iv_search);
        ivMenu.setOnClickListener(this);
        ivSearch.setOnClickListener(this);

        View index = findViewById(R.id.index);
        ivIndex = findViewById(R.id.iv_index);
        tvIndex = findViewById(R.id.tv_index);
        index.setOnClickListener(this);

        View news = findViewById(R.id.news);
        ivNews = findViewById(R.id.iv_news);
        tvNews = findViewById(R.id.tv_news);
        news.setOnClickListener(this);

        View asset = findViewById(R.id.asset);
        ivAsset = findViewById(R.id.iv_asset);
        tvAsset = findViewById(R.id.tv_asset);
        asset.setOnClickListener(this);

        View person = findViewById(R.id.person);
        ivPerson = findViewById(R.id.iv_person);
        tvPerson = findViewById(R.id.tv_person);
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
                openActivity(HenCoderMainActivity.class);
                break;
            case 1:
                openActivity(MainTransitionActivity.class);
                break;
            case 2:
                openActivity(MainWidgetActivity.class);
                break;
            case 3:
                openActivity(MainGoogleActivity.class);
                break;
            case 4:
                openActivity(MainMaterialDesignActivity.class);
                break;
            case 5:
                openActivity(JetPackActivity.class);
                break;
            case 6:
                openActivity(MainAndroidWidgetActivity.class);
                break;
        }
    }

    private void initDrawerData() {
        list = new ArrayList<>();
        Drawer drawer = new Drawer("Hen Coder", 0);
        list.add(drawer);
        drawer = new Drawer("过渡动画", 0);
        list.add(drawer);
        drawer = new Drawer("View 绘制", 0);
        list.add(drawer);
        drawer = new Drawer("Google 官方示例", 0);
        list.add(drawer);
        drawer = new Drawer("Material Design", 0);
        list.add(drawer);
        drawer = new Drawer("Android JetPack", 0);
        list.add(drawer);
        drawer = new Drawer("Android 官方控件", 0);
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
