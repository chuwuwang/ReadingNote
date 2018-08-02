package widget.sample;


import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import home.BaseAppCompatActivity;
import com.nsz.android.R;

public class ColorMatrixOperateActivity extends BaseAppCompatActivity {

    ImageView ivPic;
    Button btnSaturation1, btnSaturation2, btnSaturation0;
    Button btnContrastHigh, btnContrastLow;
    Button btnBrightnessHigh, btnBrightnessLow;
    Button btnEffect1, btnEffect2, btnEffect3, btnEffect4, btnEffect5, btnEffect6, btnEffect7, btnEffect8, btnEffect9;

    Bitmap srcBitmap;

    int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_activity_color_matrix);

        initToolbarBringBack();

        ivPic = (ImageView) findViewById(R.id.iv_pic);

        btnSaturation1 = (Button) findViewById(R.id.btn_saturation_1);
        btnSaturation2 = (Button) findViewById(R.id.btn_saturation_2);
        btnSaturation0 = (Button) findViewById(R.id.btn_saturation_0);
        btnSaturation1.setOnClickListener(this);
        btnSaturation2.setOnClickListener(this);
        btnSaturation0.setOnClickListener(this);

        btnContrastHigh = (Button) findViewById(R.id.btn_high_contrast);
        btnContrastLow = (Button) findViewById(R.id.btn_low_contrast);
        btnContrastHigh.setOnClickListener(this);
        btnContrastLow.setOnClickListener(this);

        btnBrightnessHigh = (Button) findViewById(R.id.btn_high_brightness);
        btnBrightnessLow = (Button) findViewById(R.id.btn_low_brightness);
        btnBrightnessHigh.setOnClickListener(this);
        btnBrightnessLow.setOnClickListener(this);

        btnEffect1 = (Button) findViewById(R.id.btn_effect1);
        btnEffect1.setOnClickListener(this);
        btnEffect2 = (Button) findViewById(R.id.btn_effect2);
        btnEffect2.setOnClickListener(this);
        btnEffect3 = (Button) findViewById(R.id.btn_effect3);
        btnEffect3.setOnClickListener(this);
        btnEffect4 = (Button) findViewById(R.id.btn_effect4);
        btnEffect4.setOnClickListener(this);
        btnEffect5 = (Button) findViewById(R.id.btn_effect5);
        btnEffect5.setOnClickListener(this);
        btnEffect6 = (Button) findViewById(R.id.btn_effect6);
        btnEffect6.setOnClickListener(this);
        btnEffect7 = (Button) findViewById(R.id.btn_effect7);
        btnEffect7.setOnClickListener(this);
        btnEffect8 = (Button) findViewById(R.id.btn_effect8);
        btnEffect8.setOnClickListener(this);
        btnEffect9 = (Button) findViewById(R.id.btn_effect9);
        btnEffect9.setOnClickListener(this);

        srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.matrix_camera);
        ivPic.setImageBitmap(srcBitmap);
        height = srcBitmap.getHeight();
        width = srcBitmap.getWidth();
    }

    @Override
    public void onClick(View v) {
        if (v == btnSaturation1) {
            setSaturation(5);
        } else if (v == btnSaturation2) {
            setSaturation(0.6f);
        } else if (v == btnSaturation0) {
            setSaturation(0);
        } else if (v == btnBrightnessHigh) {
            setBrightness(90);
        } else if (v == btnBrightnessLow) {
            setBrightness(1);
        } else if (v == btnContrastHigh) {
            setContrast(5);
        } else if (v == btnContrastLow) {
            setContrast(1);
        } else if (v == btnEffect1) {
            setNostalgia();
        } else if (v == btnEffect2) {
            setDisColor();
        } else if (v == btnEffect3) {
            setEffect3();
        } else if (v == btnEffect4) {
            setEffect4();
        } else if (v == btnEffect5) {
            setEffect5();
        } else if (v == btnEffect6) {
            setEffect6();
        } else if (v == btnEffect7) {
            setEffect7();
        } else if (v == btnEffect8) {
            setEffect8();
        } else if (v == btnEffect9) {
            setEffect9();
        }
    }

    private void setSaturation(float val) {
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        // 设置饱和度
        colorMatrix.setSaturation(val);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        ivPic.setImageBitmap(bmp);
    }

    private void setBrightness(float val) {
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                1, 0, 0, 0, val, // - red vector
                0, 1, 0, 0, val, // - green vector
                0, 0, 1, 0, val, // - blue vector
                0, 0, 0, 1, 0     // - alpha vector
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        ivPic.setImageBitmap(bmp);
    }

    private void setContrast(float val) {
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                val, 0, 0, 0, 0, // - red vector
                0, val, 0, 0, 0, // - green vector
                0, 0, val, 0, 0, // - blue vector
                0, 0, 0, 1, 0     // - alpha vector
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        ivPic.setImageBitmap(bmp);
    }

    // 怀旧
    private void setNostalgia() {
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                0.393F, 0.769F, 0.189F, 0, 0,
                0.349F, 0.686F, 0.168F, 0, 0,
                0.272F, 0.534F, 0.131F, 0, 0,
                0, 0, 0, 1, 0,
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        ivPic.setImageBitmap(bmp);
    }

    private void setDisColor() {
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1.5F, 1.5F, 1.5F, 0, -1,
                1.5F, 1.5F, 1.5F, 0, -1,
                1.5F, 1.5F, 1.5F, 0, -1,
                0, 0, 0, 1, 0,
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        ivPic.setImageBitmap(bmp);
    }

    private void setEffect3() {
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 1, 0
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        ivPic.setImageBitmap(bmp);
    }

    private void setEffect4() {
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                1, 0, 11, 0, 0,
                0, 1, 0, 0, 0,
                0, 100, 1, 0, 0,
                0, 0, 0, 1, 0
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        ivPic.setImageBitmap(bmp);
    }

    private void setEffect5() {
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                1, 0, 0, 0, 0,
                0, 3, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        ivPic.setImageBitmap(bmp);
    }

    private void setEffect6() {
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        matrix.postTranslate(bmp.getWidth(), 0);
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(srcBitmap, matrix, paint);
        ivPic.setImageBitmap(bmp);
    }

    private void setEffect7() {
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Matrix matrix = new Matrix();
        matrix.setScale(1, -1);
        matrix.postTranslate(0, bmp.getHeight());
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(srcBitmap, matrix, paint);
        ivPic.setImageBitmap(bmp);
    }

    private void setEffect8() {
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0, 0, 0, 1, 0,
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        ivPic.setImageBitmap(bmp);
    }

    private void setEffect9() {
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                -1, 0, 0, 1, 1,
                0, -1, 0, 1, 1,
                0, 0, -1, 1, 1,
                0, 0, 0, 1, 0,
        });
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        ivPic.setImageBitmap(bmp);
    }

}
