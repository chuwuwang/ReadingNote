package material.design;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nsz.android.R;

import com.nsz.android.home.BaseAppCompatActivity;

/*
    关键字标签组件

    单选或多选功能需要设置android:checkable="true"才可以实现，官方给的设置方式是app:checkable=“true”，
    会报错找不到，可能是bug，发布正式版的时候再看看。

    Chip组件默认不支持点击，需要设置android:clickable="true"才可以。

    ChipGroup属性速查表：
    属性	介绍
    app:chipSpacing	Chip在水平&垂直方向的间距
    app:chipSpacingHorizontal	Chip在水平方向的间距
    app:chipSpacingVertical	Chip在垂直方向的间距
    app:singleLine	是否单行显示Chip，默认为false
    app:singleSelection	是否为单选模式，默认为false

    Chip属性速查表：
    属性	介绍
    app:chipBackgroundColor	Chip背景颜色
    app:chipCornerRadius	Chip圆角角度
    app:chipStrokeColor	Chip边框颜色
    app:chipStrokeWidth	Chip边框宽度
    app:rippleColor	Chip点击水波纹效果颜色
    app:chipIconEnabled	是否在Chip上显示图标，默认为true
    app:chipIcon	Chip图标（在文字左边，不能设置位置）
    app:chipIconSize	Chip图标大小
    app:closeIconEnabled	是否显示Chip关闭按钮，默认为false
    app:closeIcon	Chip关闭按钮图标
    app:closeIconTint	Chip关闭按钮着色
    app:closeIconSize	Chip关闭按钮大小
    app:checkedIconEnabled	是否显示Chip选中图标，默认为true
    app:checkedIcon	Chip选中图标
    app:chipStartPadding	Chip左内边距
    app:chipEndPadding	Chip右内边距
    app:iconStartPadding	Chip图标左内边距
    app:iconEndPadding	Chip图标右内边距
    app:textStartPadding	Chip文字左内边距
    app:textEndPadding	Chip文字右内边距
    app:closeIconStartPadding	Chip关闭图标左内边距
    app:closeIconEndPadding	Chip关闭图标右内边距

 */
public class ChipGroupAndChipActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.md_activity_chip);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        findViewById(R.id.chip_1).setOnClickListener(this);
        findViewById(R.id.chip_2).setOnClickListener(this);
        findViewById(R.id.chip_3).setOnClickListener(this);
        findViewById(R.id.chip_4).setOnClickListener(this);
        findViewById(R.id.chip_5).setOnClickListener(this);
        findViewById(R.id.chip_6).setOnClickListener(this);
        findViewById(R.id.chip_7).setOnClickListener(this);
        findViewById(R.id.chip_8).setOnClickListener(this);
        findViewById(R.id.chip_9).setOnClickListener(this);
        findViewById(R.id.chip_10).setOnClickListener(this);
        findViewById(R.id.chip_11).setOnClickListener(this);
        findViewById(R.id.chip_12).setOnClickListener(this);
        findViewById(R.id.chip_13).setOnClickListener(this);
        findViewById(R.id.chip_14).setOnClickListener(this);
        findViewById(R.id.chip_15).setOnClickListener(this);
        findViewById(R.id.chip_16).setOnClickListener(this);
        findViewById(R.id.chip_17).setOnClickListener(this);
        findViewById(R.id.chip_18).setOnClickListener(this);
        findViewById(R.id.chip_19).setOnClickListener(this);
        findViewById(R.id.chip_20).setOnClickListener(this);
        findViewById(R.id.chip_21).setOnClickListener(this);
        findViewById(R.id.chip_22).setOnClickListener(this);
    }


}