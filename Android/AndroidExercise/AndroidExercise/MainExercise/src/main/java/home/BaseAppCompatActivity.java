package home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nsz.android.R;

/**
 * @author Created by Lee64 on 2017/9/26.
 */

public class BaseAppCompatActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化带返回的Toolbar
     */
    public void initToolbarBringBack() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Navigation Icon 要设定在 setSupportActionBar 才有作用 否则会出現 back button
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                v -> finish()
        );
    }

    /**
     * 初始化带返回的Toolbar
     *
     * @param title Toolbar的标题
     */
    public void initToolbarBringBack(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                v -> finish()
        );
    }

    /**
     * 初始化带图标的Toolbar
     */
    public void initToolbarBringHome() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View v) {

    }

    public void openActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

}
