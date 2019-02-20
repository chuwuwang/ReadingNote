package material.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

import com.nsz.android.R;

import com.nsz.android.home.BaseAppCompatActivity;

/*

    TextInputLayout
    TextInputLayout用于辅助EditText,当用户输入文本时,在EditText上方显示浮动标签,这个标签的内容就是我们设置的android:hint属性.
    TextInputLayout 属于Android Design Support library,可以直接向下兼容到Android 2.2.
    TextInputLayout 继承于LinerLayout,说明它是一个布局,需要配合子控件使用才能显示想要的效果,类似ScrollView的用法

    TextInputLayout 属性说明
    属性	说明
    app:Theme	设置下划线或其他的颜色属性
    android.support.design:counterEnabled	是否显示计数器
    android.support.design:counterMaxLength	设置计数器的最大值,与counterEnabled同时使用
    android.support.design:counterTextAppearance	计数器的字体样式
    android.support.design:counterOverflowTextAppearance	输入字符大于我们限定个数字符时的字体样式
    android.support.design:errorEnabled	是否显示错误信息
    android.support.design:errorTextAppearance	错误信息的字体样式
    android.support.design:hintAnimationEnabled	是否显示hint的动画,默认true
    android.support.design:hintEnabled	是否使用hint属性,默认true
    android.support.design:hintTextAppearance	设置hint的文字样式(指运行动画效果之后的样式)
    android.support.design:passwordToggleDrawable	设置密码开关Drawable图片,于passwordToggleEnabled同时使用
    android.support.design:passwordToggleEnabled	是否显示密码开关图片,需要EditText设置inputType
    android.support.design:passwordToggleTint	设置密码开关图片颜色
    android.support.design:passwordToggleTintMode	设置密码开关图片(混合颜色模式),与passwordToggleTint同时使用

 */
public class TextInputLayoutActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseStatus();
        setContentView(R.layout.md_activity_text_input_layout);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        TextInputLayout password = findViewById(R.id.til_password);
        TextInputEditText textInputEditText = findViewById(R.id.et_password);
        textInputEditText.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.length() > 16) {
                            password.setError("error password");
                        } else {
                            password.setError("");
                        }
                    }

                }
        );
    }

}
