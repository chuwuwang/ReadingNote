package widget.sample;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.TextView;

import com.nsz.android.R;

import home.BaseAppCompatActivity;

public class PropertyAnimatorAddNumberActivity extends BaseAppCompatActivity {

    private TextView tvNumber;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_activity_property_animation_add_number);

        initToolbarBringBack();

        tvNumber = (TextView) findViewById(R.id.tv_number);

        person = new Person();
        person.setName("张三");
        person.setAge(0);
        ObjectAnimator animator = ObjectAnimator.ofInt(person, "age", 1, 100);
        animator.addUpdateListener(
                animation -> {
                    int value = (int) animation.getAnimatedValue();
                    person.setAge(value);
                    String string = person.toString();
                    tvNumber.setText(string);
                }
        );
        animator.setDuration(4000);
        animator.start();
    }

    private class Person {

        private String name;
        private int age;

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + "]";
        }

    }

}
