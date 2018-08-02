package transition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.FrameLayout;

import home.BaseAppCompatActivity;
import com.nsz.android.R;

/**
 * @author Created by Lee64 on 2017/9/26.
 */

public class CustomTransitionActivity extends BaseAppCompatActivity {

    private static final String STATE_CURRENT_SCENE = "current_scene";

    private Scene[] scenes;
    private Transition transition;
    private int currentScene;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_activity_custom);
        if (null != savedInstanceState) {
            currentScene = savedInstanceState.getInt(STATE_CURRENT_SCENE);
        }
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        FrameLayout container = (FrameLayout) findViewById(R.id.container);
        scenes = new Scene[]{
                Scene.getSceneForLayout(container, R.layout.transition_include_scene_custom_one, this),
                Scene.getSceneForLayout(container, R.layout.transition_include_scene_custom_two, this),
                Scene.getSceneForLayout(container, R.layout.transition_include_scene_custom_three, this),
        };
        transition = new ChangeColor();
        Scene scene = scenes[currentScene % scenes.length];
        TransitionManager.go(scene);

        View changeState = findViewById(R.id.show_next_scene);
        changeState.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                currentScene = (currentScene + 1) % scenes.length;
                TransitionManager.go(scenes[currentScene], transition);
            }

        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_CURRENT_SCENE, currentScene);
    }

}
