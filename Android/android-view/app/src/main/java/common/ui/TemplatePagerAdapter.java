package common.ui;

import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class TemplatePagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> list;
    private SparseArray<Fragment> registerFragments = new SparseArray<>();

    public TemplatePagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Note
        // if (position == 0) {
        //     MyFragment1.newInstance(...);
        // } else if (position == 1) {
        //     MyFragment2.newInstance(...);
        // }
        // can not list.get(position)
        return list.get(position);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registerFragments.put(position, fragment);
        return fragment;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        registerFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisterFragment(int position) {
        return registerFragments.get(position);
    }

}