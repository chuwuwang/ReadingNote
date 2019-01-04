package material.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.bottomappbar.BottomAppBar;

import com.nsz.android.R;

import com.nsz.android.home.BaseAppCompatActivity;

/*
    底部标题栏

    BottomAppBar属性速查表：

    属性	介绍
    app:backgroundTint	BottomAppBar背景着色
    app:fabAlignmentMode	FAB位置（居中或居右），默认为居右
    app:fabAttached	是否绑定FAB，默认为true
    app:fabCradleMargin	BottomAppBar与FAB的距离，默认为5dp
    app:fabCradleRoundedCornerRadius	BottomAppBar与FAB相邻处的圆角角度，默认为8dp
    app:fabCradleVerticalOffset	FAB在BottomAppBar中的垂直偏移量，默认为0dp

    底部应用栏 必须 有一个分配给它的菜单才能显示在屏幕上. 这可以通过编码方式完成,如下所示:
    bottom_app_bar.replaceMenu(R.menu.main)

    app:fabAttached: FAB (Floating Action Button) 是否已经附加到底部应用栏. 你可以使用底部应用栏的 ID,
    在你希望附加 FAB 组件上使用 app:layout_anchor 来附加一个 FAB. 如果附加了 FAB, 它将插入底部应用栏,
    否则 FAB 将保持在底部应用栏上方.
 */
public class BottomAppBarActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.md_activity_bottom_app_bar);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        BottomAppBar bottomAppBar = findViewById(R.id.bottom_app_bar);
        bottomAppBar.replaceMenu(R.menu.menu_widget_path);
    }

}
