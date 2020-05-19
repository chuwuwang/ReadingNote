package com.nsz.kotlin.aac.architecture.data.binding;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Toast;

import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;

@BindingMethods(
        @BindingMethod(type = MyTextView.class, attribute = "showToast", method = "showInputToast")
)
public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void showInputToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

}