package transition;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.TextView;

import home.BaseAppCompatActivity;
import com.nsz.android.R;

import java.util.List;

/**
 * @author Created by Lee64 on 2017/9/28.
 */

public class CircularRevealFragmentActivity extends BaseAppCompatActivity {

    private boolean isInBackAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_activity_circular_reveal_fragment);
        initToolbarBringBack();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, SimpleFragment.newInstance(0)).commit();
        }
    }

    public static class SimpleFragment extends Fragment {

        private static final int[] COLOR_LIST = new int[]{
                0xff33b5e5,
                0xff99cc00,
                0xffff8800,
                0xffaa66cc,
                0xffff4444,
        };

        private CircularRevealLayout revealLayout;
        private TextView textView;
        private int index;


        public static SimpleFragment newInstance(int index) {
            SimpleFragment fragment = new SimpleFragment();
            Bundle args = new Bundle();
            args.putInt("index", index);
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.transition_fragment_circular_reveal_simple, container, false);
            revealLayout = (CircularRevealLayout) rootView.findViewById(R.id.reveal_layout);
            textView = (TextView) rootView.findViewById(R.id.text);
            index = getArguments().getInt("index");
            textView.setBackgroundColor(COLOR_LIST[index % 5]);
            textView.setText("Fragment " + index);
            revealLayout.setContentShown(false);
            revealLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        revealLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        revealLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                    revealLayout.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            revealLayout.show();
                        }

                    }, 50);
                }

            });
            revealLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getFragmentManager().beginTransaction()
                            .addToBackStack(null)
                            .add(R.id.container, SimpleFragment.newInstance(index + 1))
                            .commit();
                }

            });
            return rootView;
        }

        public void onBackPressed(Animation.AnimationListener listener) {
            revealLayout.hide(listener);
        }

    }

    @Override
    public void onBackPressed() {
        if (isInBackAnimation) return;
        FragmentManager manager = getSupportFragmentManager();
        List<Fragment> fragments = manager.getFragments();
        int lastCount = manager.getBackStackEntryCount();
        if (fragments != null && lastCount > 0) {
            Fragment lastFragment = fragments.get(lastCount);
            if (lastFragment != null && lastFragment instanceof SimpleFragment) {
                SimpleFragment simpleFragment = (SimpleFragment) lastFragment;
                simpleFragment.onBackPressed(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        isInBackAnimation = true;
                    }


                    @Override
                    public void onAnimationEnd(Animation animation) {
                        isInBackAnimation = false;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                });
            }
        }
        super.onBackPressed();
    }


}
