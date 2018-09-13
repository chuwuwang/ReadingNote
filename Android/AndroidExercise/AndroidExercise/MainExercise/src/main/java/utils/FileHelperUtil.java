package utils;

import android.content.Context;
import android.util.Log;

import java.io.File;

import home.Constant;

public class FileHelperUtil {

    public static void getCacheDir(Context context) {
        File cacheDir = context.getCacheDir();
        String cacheDirPath = cacheDir.getAbsolutePath();
        Log.i(Constant.TAG, cacheDirPath);
    }

}
