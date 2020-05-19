package com.nsz.kotlin.aac.architecture.data.binding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.nsz.kotlin.BR;

public class ObservableBean extends BaseObservable {

    private String name;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
        notifyPropertyChanged(BR.name);
    }

}