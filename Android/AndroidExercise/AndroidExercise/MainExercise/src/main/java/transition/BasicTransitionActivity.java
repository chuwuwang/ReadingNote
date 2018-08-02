package transition;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import home.BaseAppCompatActivity;
import com.nsz.android.R;

/**
 * @author Created by Lee64 on 2017/9/26.
 */

public class BasicTransitionActivity extends BaseAppCompatActivity {

    private Scene scene1;
    private Scene scene2;
    private Scene scene3;

    private ViewGroup sceneRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_activity_basic);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        sceneRoot = (ViewGroup) findViewById(R.id.scene_root);
        scene1 = new Scene(sceneRoot, sceneRoot.findViewById(R.id.container));
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.transition_include_scene_base_two, this);
        scene3 = Scene.getSceneForLayout(sceneRoot, R.layout.transition_include_scene_base_three, this);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.select_scene);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.select_scene_one:
                        TransitionManager.go(scene1);
                        break;
                    case R.id.select_scene_two:
                        TransitionManager.go(scene2);
                        break;
                    case R.id.select_scene_three:
                        AutoTransition autoTransition = new AutoTransition();
                        autoTransition.addTransition(new ChangeBounds())
                                .addTransition(new Fade()).addTarget(R.id.transition_title);
                        TransitionManager.go(scene3, autoTransition);
                        break;
                    case R.id.select_scene_four:
                        TransitionManager.beginDelayedTransition(sceneRoot);
                        View square = sceneRoot.findViewById(R.id.transition_square);
                        ViewGroup.LayoutParams layoutParams = square.getLayoutParams();
                        int newSize = 200;
                        layoutParams.width = newSize;
                        layoutParams.height = newSize;
                        square.setLayoutParams(layoutParams);
                        break;
                }
            }

        });
    }


}
