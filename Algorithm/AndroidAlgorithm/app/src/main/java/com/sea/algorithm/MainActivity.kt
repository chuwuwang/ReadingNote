package com.sea.algorithm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sea.algorithm.sort.BucketSort
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method
        sample_text.text = stringFromJNI()

        val sortString = BucketSort().bucketSort(6, 102)
        Log.d(TAG, "sortString: $sortString")
        sample_text.text = sortString
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    private external fun stringFromJNI(): String

    companion object {
        const val TAG = "MainActivity"

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

}