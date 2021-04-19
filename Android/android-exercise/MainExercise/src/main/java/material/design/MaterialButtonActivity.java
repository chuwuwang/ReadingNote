package material.design;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nsz.android.R;

import com.nsz.android.home.BaseAppCompatActivity;

/*
    Material风格的Button

    注意：MaterialButton必须设置textAppearance属性，如果没有的话会报错，不知道是不是bug。

    MaterialButton属性速查表：

    属性	介绍
    app:backgroundTint	按钮背景着色
    app:backgroundTintMode	按钮背景的着色模式
    app:icon	按钮图标（在文字左边，不能设置位置）
    app:iconSize	按钮图标大小
    app:iconPadding	按钮图标的内边距
    app:iconTint	按钮图标着色
    app:iconTintMode	按钮图标的着色模式
    app:additionalPaddingStartForIcon	按钮图标的左内边距
    app:additionalPaddingEndForIcon	按钮图标的右内边距
    app:strokeColor	按钮边框颜色
    app:strokeWidth	按钮边框宽度
    app:cornerRadius	按钮圆角角度
    app:rippleColor	按钮点击水波纹效果颜色

 */
public class MaterialButtonActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.md_activity_material_button);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        findViewById(R.id.mb_1).setOnClickListener(this);
        findViewById(R.id.mb_2).setOnClickListener(this);
        findViewById(R.id.mb_3).setOnClickListener(this);
        findViewById(R.id.mb_4).setOnClickListener(this);
    }


}
