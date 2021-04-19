package com.jet.pack.data.binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.base.core.BaseAppCompatActivity;
import com.jet.pack.R;
import com.jet.pack.databinding.DataBindingActivityMainBinding;
import com.jet.pack.model.Teacher;

public class DataBindingActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.data_binding_activity_main);
        Teacher teacher = new Teacher(30, "xie");
        binding.setTeacher(teacher);

        initToolbarBringBack("DataBinding");
    }


}
