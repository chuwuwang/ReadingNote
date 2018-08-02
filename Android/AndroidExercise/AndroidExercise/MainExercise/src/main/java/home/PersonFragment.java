package home;

import com.nsz.android.R;

/**
 * 个人
 *
 * @author Created by Lee64 on 2017/10/3.
 */

public class PersonFragment extends BaseFragment {

    public static PersonFragment newInstance() {
        PersonFragment fragment = new PersonFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_person;
    }

}
