package paint;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.nsz.android.R;

import java.util.ArrayList;
import java.util.List;

import com.nsz.android.home.BaseAppCompatActivity;

/**
 * 对Paint操作的主界面
 */
public class MainHenCoderPaintActivity extends BaseAppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.layout.sample_linear_gradient, R.string.title_linear_gradient, R.layout.practice_linear_gradient));
        pageModels.add(new PageModel(R.layout.sample_radial_gradient, R.string.title_radial_gradient, R.layout.practice_radial_gradient));
        pageModels.add(new PageModel(R.layout.sample_sweep_gradient, R.string.title_sweep_gradient, R.layout.practice_sweep_gradient));
        pageModels.add(new PageModel(R.layout.sample_bitmap_shader, R.string.title_bitmap_shader, R.layout.practice_bitmap_shader));
        pageModels.add(new PageModel(R.layout.sample_compose_shader, R.string.title_compose_shader, R.layout.practice_compose_shader));
        pageModels.add(new PageModel(R.layout.sample_lighting_color_filter, R.string.title_lighting_color_filter, R.layout.practice_lighting_color_filter));
        pageModels.add(new PageModel(R.layout.sample_color_mask_color_filter, R.string.title_color_matrix_color_filter, R.layout.practice_color_matrix_color_filter));
        pageModels.add(new PageModel(R.layout.sample_xfermode, R.string.title_transfer_mode, R.layout.practice_xfermode));
        pageModels.add(new PageModel(R.layout.sample_stroke_cap, R.string.title_stroke_cap, R.layout.practice_stroke_cap));
        pageModels.add(new PageModel(R.layout.sample_stroke_join, R.string.title_stroke_join, R.layout.practice_stroke_join));
        pageModels.add(new PageModel(R.layout.sample_stroke_miter, R.string.title_stroke_miter, R.layout.practice_stroke_miter));
        pageModels.add(new PageModel(R.layout.sample_path_effect, R.string.title_path_effect, R.layout.practice_path_effect));
        pageModels.add(new PageModel(R.layout.sample_shadow_layer, R.string.title_shader_layer, R.layout.practice_shadow_layer));
        pageModels.add(new PageModel(R.layout.sample_mask_filter, R.string.title_mask_filter, R.layout.practice_mask_filter));
        pageModels.add(new PageModel(R.layout.sample_fill_path, R.string.title_fill_path, R.layout.practice_fill_path));
        pageModels.add(new PageModel(R.layout.sample_text_path, R.string.title_text_path, R.layout.practice_text_path));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_activity_coder_paint);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                PageModel pageModel = pageModels.get(position);
                return PageFragment.newInstance(pageModel.sampleLayoutRes, pageModel.practiceLayoutRes);
            }

            @Override
            public int getCount() {
                return pageModels.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getString(pageModels.get(position).titleRes);
            }

        };
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        initToolbarBringBack();
    }

    private final class PageModel {

        @LayoutRes
        int sampleLayoutRes;
        @StringRes
        int titleRes;
        @LayoutRes
        int practiceLayoutRes;

        PageModel(@LayoutRes int sampleLayoutRes, @StringRes int titleRes, @LayoutRes int practiceLayoutRes) {
            this.sampleLayoutRes = sampleLayoutRes;
            this.titleRes = titleRes;
            this.practiceLayoutRes = practiceLayoutRes;
        }

    }

}
