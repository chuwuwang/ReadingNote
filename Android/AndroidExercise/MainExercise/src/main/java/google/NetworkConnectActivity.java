package google;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nsz.android.R;

import google.network.DownloadCallback;
import google.network.NetworkFragment;
import com.nsz.android.home.BaseAppCompatActivity;

/**
 * @author Created by Lee64 on 2018/1/24.
 */

public class NetworkConnectActivity extends BaseAppCompatActivity implements DownloadCallback {

    private TextView mDataText;
    private NetworkFragment mNetworkFragment;
    private boolean mDownloading = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_activity_network_connect);
        initView();
        initToolbarBringBack();
    }

    private void initView() {
        mDataText = findViewById(R.id.tv_data);
        Button start = findViewById(R.id.btn_start);
        Button cancel = findViewById(R.id.btn_cancel);
        start.setOnClickListener(this);
        cancel.setOnClickListener(this);
        mNetworkFragment = NetworkFragment.getInstance(getSupportFragmentManager(), "https://www.oschina.net/");
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.btn_start:
                startDownload();
                break;
            case R.id.btn_cancel:
                finishDownloading();
                mDataText.setText("");
                break;
            default:
                break;
        }
    }

    private void startDownload() {
        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.
            mNetworkFragment.startDownload();
            mDownloading = true;
        }
    }

    @Override
    public void updateFromDownload(String result) {
        if (result != null) {
            mDataText.setText(result);
        } else {
            mDataText.setText("network error");
        }
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        switch (progressCode) {
            // You can add UI behavior for progress updates here.
            case Progress.ERROR:
                break;
            case Progress.CONNECT_SUCCESS:
                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:
                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
                mDataText.setText("" + percentComplete + "%");
                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
                break;
        }
    }

    @Override
    public void finishDownloading() {
        mDownloading = false;
        if (mNetworkFragment != null) {
            mNetworkFragment.cancelDownload();
        }
    }


}
