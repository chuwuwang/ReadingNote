package transition;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Created by Lee64 on 2017/9/26.
 */

public class ChangeColor extends Transition {

    /**
     * Key to store a color value in TransitionValues object
     */
    private static final String PROPERTY_NAME_BACKGROUND = "custom_transition:change_color:background";

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (null == startValues || null == endValues) {
            return null;
        }
        final View view = endValues.view;
        Drawable startBackground = (Drawable) startValues.values.get(PROPERTY_NAME_BACKGROUND);
        Drawable endBackground = (Drawable) endValues.values.get(PROPERTY_NAME_BACKGROUND);
        if (startBackground instanceof ColorDrawable && endBackground instanceof ColorDrawable) {
            ColorDrawable startColor = (ColorDrawable) startBackground;
            ColorDrawable endColor = (ColorDrawable) endBackground;
            if (startColor.getColor() != endColor.getColor()) {
                ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), startColor.getColor(), endColor.getColor());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Object values = animation.getAnimatedValue();
                        if (null != values) {
                            view.setBackgroundColor((Integer) values);
                        }
                    }

                });
                return valueAnimator;
            }
        }
        return null;
    }

    private void captureValues(TransitionValues transitionValues) {
        // Capture the property values of views for later use
        transitionValues.values.put(PROPERTY_NAME_BACKGROUND, transitionValues.view.getBackground());
    }

}
