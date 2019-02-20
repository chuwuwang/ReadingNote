package com.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import com.jakewharton.disklrucache.DiskLruCache;
import com.loader.utils.ImageResize;
import com.loader.utils.ThreadPollExecutorUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Created by Lee64 on 2018/2/24.
 */

public class ImageLoader {

    private static final String TAG = "ImageLoader";

    private static final int DISK_CACHE_INDEX = 0;
    private static final int IO_BUFFER_SIZE = 8 * 1024;
    private static final long DISK_CACHE_SIZE = 50 * 1024 * 1024;
    private static final int TAG_KEY_URI = android.R.id.selectedIcon;

    private boolean misDiskLruCacheCreate = false;

    private Context mContext;
    private LruCache<String, Bitmap> mMemoryCache;
    private DiskLruCache mDiskLruCache;

    private Handler mMainHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            LoaderResult result = (LoaderResult) msg.obj;
            ImageView imageView = result.imageView;
            String uri = (String) imageView.getTag(TAG_KEY_URI);
            if (uri.equals(result.uri)) {
                imageView.setImageBitmap(result.bitmap);
            } else {
                Log.w(TAG, "set image bitmap, but url has change, ignored.");
            }
        }

    };

    private ImageLoader(Context context) {
        mContext = context;
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }

        };

        File diskCacheDir = getDiskCacheDir(mContext, "bitmap");
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdirs();
        }

        if (getUsableSpace(diskCacheDir) > DISK_CACHE_SIZE) {
            try {
                mDiskLruCache = DiskLruCache.open(diskCacheDir, 1, 1, DISK_CACHE_SIZE);
                misDiskLruCacheCreate = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static ImageLoader build(Context context) {
        return new ImageLoader(context);
    }

    public void bindBitmap(final String url, final ImageView imageView) {
        bindBitmap(url, imageView, 0, 0);
    }

    public void bindBitmap(final String url, final ImageView imageView, final int reqWidth, final int reqHeight) {
        imageView.setTag(TAG_KEY_URI, url);
        final Bitmap bitmap = loadBitmapFromMemoryCache(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        Runnable loadBitmapTask = new Runnable() {

            @Override
            public void run() {
                Bitmap loadBitmap = loadBitmap(url, reqWidth, reqHeight);
                if (loadBitmap != null) {
                    LoaderResult result = new LoaderResult(imageView, url, loadBitmap);
                    mMainHandler.obtainMessage(0x01, result).sendToTarget();
                }
            }

        };
        ThreadPollExecutorUtil.THREAD_POLL_EXECUTOR.execute(loadBitmapTask);
    }

    public Bitmap loadBitmap(String url, int reqWidth, int reqHeight) {
        Bitmap bitmap = loadBitmapFromMemoryCache(url);
        if (bitmap != null) {
            Log.d(TAG, "loadBitmap: " + url);
            return bitmap;
        }
        try {
            bitmap = loadBitmapFromDiskCache(url, reqWidth, reqHeight);
            if (bitmap != null) {
                Log.d(TAG, "loadBitmapFromDiskCache: " + url);
                return bitmap;
            }
            bitmap = loadBitmapFromHttp(url, reqWidth, reqHeight);
            Log.d(TAG, "loadBitmapFromHttp: " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bitmap == null && !misDiskLruCacheCreate) {
            bitmap = downloadBitmapFormUrl(url);
        }
        return bitmap;
    }

    private Bitmap loadBitmapFromMemoryCache(String url) {
        final String key = hashKeyFormUrl(url);
        return getBitmapFromMemoryCache(key);
    }

    private Bitmap loadBitmapFromDiskCache(String url, int reqWidth, int reqHeight) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.w(TAG, "load bitmap from UI Thread, it's not recommended.");
        }
        if (mDiskLruCache == null) {
            return null;
        }
        Bitmap bitmap = null;
        String key = hashKeyFormUrl(url);
        DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
        if (snapshot != null) {
            FileInputStream fileInputStream = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);
            FileDescriptor fd = fileInputStream.getFD();
            bitmap = ImageResize.decodeSampledBitmapFromFileDescriptor(fd, reqWidth, reqHeight);
            if (bitmap != null) {
                addBitmapToMemoryCache(key, bitmap);
            }
        }
        return bitmap;
    }

    private Bitmap loadBitmapFromHttp(String url, int reqWidth, int reqHeight) throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("can not visit network from UI Thread.");
        }
        if (mDiskLruCache == null) {
            return null;
        }
        final String key = hashKeyFormUrl(url);
        DiskLruCache.Editor editor = mDiskLruCache.edit(key);
        if (editor != null) {
            OutputStream outputStream = editor.newOutputStream((int) DISK_CACHE_INDEX);
            if (downloadUrlToStream(url, outputStream)) {
                editor.commit();
            } else {
                editor.abort();
            }
            mDiskLruCache.flush();
        }
        return loadBitmapFromDiskCache(url, reqWidth, reqHeight);
    }

    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            out = new BufferedOutputStream(outputStream, IO_BUFFER_SIZE);

            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (IOException e) {
            Log.e(TAG, "downloadUrlToStream: 下载图片出错");
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private Bitmap downloadBitmapFormUrl(String urlString) {
        Bitmap bitmap = null;
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            Log.e(TAG, "downloadBitmapFormUrl: 下载图片出错");
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    private Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            // /sdcard/Android/data/<application package>/cache
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            // /data/data/<application package>/cache
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    private long getUsableSpace(File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return path.getUsableSpace();
        }
        final StatFs statFs = new StatFs(path.getPath());
        return statFs.getBlockSize() * statFs.getAvailableBlocks();
    }

    private String hashKeyFormUrl(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    private static class LoaderResult {

        ImageView imageView;
        String uri;
        Bitmap bitmap;

        LoaderResult(ImageView imageView, String uri, Bitmap bitmap) {
            this.imageView = imageView;
            this.uri = uri;
            this.bitmap = bitmap;
        }

    }

}
