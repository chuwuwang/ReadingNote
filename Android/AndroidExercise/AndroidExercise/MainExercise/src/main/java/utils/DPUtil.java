package utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class DPUtil {

    public static float dp2px(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float px = dp * (displayMetrics.densityDpi / 160f);
        return px;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


}
