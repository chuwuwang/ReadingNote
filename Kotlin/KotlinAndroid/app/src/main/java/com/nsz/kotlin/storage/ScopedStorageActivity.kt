package com.nsz.kotlin.storage

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import java.io.ByteArrayOutputStream
import java.io.File

class ScopedStorageActivity : AppCompatActivity() {

    companion object {
        const val TAG = "ScopedStorageActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_scope)
        initView()
    }

    private fun initView() {
        internalStorage()
        checkPermission()
    }

    private fun internalStorage() {
        val internalDirView = findViewById<TextView>(R.id.tv_internal_storage_dir)
        val internalFileView = findViewById<TextView>(R.id.tv_internal_storage_file)
        val internalDataView = findViewById<TextView>(R.id.tv_internal_storage_data)
        val internalCacheView = findViewById<TextView>(R.id.tv_internal_storage_cache)

        val dirFile = getDir("Joe", Context.MODE_APPEND)
        val bool = dirFile.exists()
        var text = if (bool) {
            " 目录已存在"
        } else {
            dirFile.mkdirs()
            " 新建该目录"
        }
        text = "$dirFile $text"
        Log.d(TAG, "getDir(): $text")
        internalDirView.text = text

        text = "$filesDir"
        Log.d(TAG, "getFilesDir(): $text")
        internalFileView.text = text

        text = "$cacheDir"
        Log.d(TAG, "getCacheDir(): $text")
        internalCacheView.text = text

        text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            "$dataDir"
        } else {
            "API小于24"
        }
        Log.d(TAG, "getDataDir(): $text")
        internalDataView.text = text

        val cusName = "202003211800.text"
        val cusFile = File(filesDir, cusName)
        cusFile.createNewFile()
        Log.d(TAG, "内部存储自定义文件: $cusFile")

        val data = "Hello World".toByteArray()
        writeDataByInternalStorage(data, cusName, Context.MODE_APPEND)

        val dataStr = readDataByInternalStorage(cusName)
        Log.d(TAG, "读取内部存储自定义文件: $dataStr")
    }

    private fun externalStoragePrivate() {
        try {
            val externalFileView = findViewById<TextView>(R.id.tv_external_storage_file)
            val externalCacheView = findViewById<TextView>(R.id.tv_external_storage_cache)
            val externalMediaView = findViewById<TextView>(R.id.tv_external_storage_media)

            // getExternalFilesDir()
            var text: String
            var externalFiles = getExternalFilesDir(null)
            Log.d(TAG, "getExternalFilesDir: $externalFiles")
            text = "$externalFiles \n"

            val externalFilesDirs = getExternalFilesDirs(null)
            for (i in externalFilesDirs.indices) {
                val file = externalFilesDirs[i]
                Log.d(TAG, "getExternalFilesDirs[$i]: $file")
                text += "$file \n"
            }

            externalFiles = getExternalFilesDir(Environment.DIRECTORY_MUSIC)
            Log.d(TAG, "getExternalFilesDir_DIRECTORY_MUSIC: $externalFiles")
            text += "$externalFiles \n"

            externalFiles = getExternalFilesDir(Environment.DIRECTORY_MOVIES)
            Log.d(TAG, "getExternalFilesDir_DIRECTORY_MOVIES: $externalFiles")
            text += "$externalFiles \n"

            externalFiles = getExternalFilesDir(Environment.DIRECTORY_ALARMS)
            Log.d(TAG, "getExternalFilesDir_DIRECTORY_ALARMS: $externalFiles")
            text += "$externalFiles \n"

            externalFiles = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            Log.d(TAG, "getExternalFilesDir_DIRECTORY_PICTURES: $externalFiles")
            text += "$externalFiles \n"

            externalFiles = getExternalFilesDir(Environment.DIRECTORY_RINGTONES)
            Log.d(TAG, "getExternalFilesDir_DIRECTORY_RINGS: $externalFiles")
            text += "$externalFiles \n"

            externalFiles = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
            Log.d(TAG, "getExternalFilesDir_DIRECTORY_DOCUMENTS: $externalFiles")
            text += "$externalFiles \n"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                externalFiles = getExternalFilesDir(Environment.DIRECTORY_SCREENSHOTS)
                Log.d(TAG, "getExternalFilesDir_DIRECTORY_SCREENSHOTS: $externalFiles")
                text += "$externalFiles \n"
            }

            externalFiles = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            Log.d(TAG, "getExternalFilesDir_DIRECTORY_DOWNLOADS: $externalFiles")
            text += "$externalFiles"

            externalFileView.text = text

            // getExternalCacheDir()
            text = "$externalCacheDir \n"
            Log.d(TAG, "getExternalCacheDir: $externalCacheDir")

            for (i in externalCacheDirs.indices) {
                val file = externalCacheDirs[i]
                Log.d(TAG, "getExternalCacheDirs[$i]: $file")
                text += "$file"
                if (i != externalCacheDirs.size - 1) {
                    text += "\n"
                }
            }
            externalCacheView.text = text

            // getExternalMediaDirs()
            text = ""
            for (i in externalMediaDirs.indices) {
                val file = externalMediaDirs[i]
                Log.d(TAG, "getExternalMediaDirs[$i]: $file")
                text += "$file"
                if (i != externalMediaDirs.size - 1) {
                    text += "\n"
                }
            }
            externalMediaView.text = text
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun externalStoragePublic() {
        try {
            val rootView = findViewById<TextView>(R.id.tv_external_storage_public_root)
            val dataView = findViewById<TextView>(R.id.tv_external_storage_public_data)
            val cacheView = findViewById<TextView>(R.id.tv_external_storage_public_cache)
            val dirView = findViewById<TextView>(R.id.tv_external_storage_public_dir)
            val dirSpicView = findViewById<TextView>(R.id.tv_external_storage_public_dir_spic)

            val rootDirectory = Environment.getRootDirectory()
            var text = "$rootDirectory"
            Log.d(TAG, "getRootDirectory: $text")
            rootView.text = text

            val dataDirectory = Environment.getDataDirectory()
            text = "$dataDirectory"
            Log.d(TAG, "getDataDirectory: $text")
            dataView.text = text

            val downloadCacheDirectory = Environment.getDownloadCacheDirectory()
            text = "$downloadCacheDirectory"
            Log.d(TAG, "getDownloadCacheDirectory: $text")
            cacheView.text = text

            val externalStorageState = Environment.getExternalStorageState()
            Log.d(TAG, "getExternalStorageState: $externalStorageState")

            val externalStorageDirectory = Environment.getExternalStorageDirectory()
            text = "$externalStorageDirectory"
            Log.d(TAG, "getExternalStorageDirectory: $text")
            dirView.text = text

            val externalStoragePublicDirectoryMusic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
            text = "$externalStoragePublicDirectoryMusic \n"
            Log.d(TAG, "getExternalStoragePublicDirectory_DIRECTORY_MUSIC: $text")

            val externalStoragePublicDirectoryDocuments = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            text += "$externalStoragePublicDirectoryDocuments"
            Log.d(TAG, "getExternalStoragePublicDirectory_DIRECTORY_DOCUMENTS: $externalStoragePublicDirectoryDocuments")
            dirSpicView.text = text

            val cusDir = File(externalStorageDirectory, "Joe")
            cusDir.mkdirs()
            Log.d(TAG, "外部共有存储自定义文件夹: $cusDir")

            try {
                val cusFile1 = File(cusDir, "hello.text")
                cusFile1.createNewFile()
                Log.d(TAG, "外部共有存储自定义文件: $cusFile1")
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            try {
                val cusFile2 = File(externalStorageDirectory, "hello.text")
                cusFile2.createNewFile()
                Log.d(TAG, "外部共有存储自定义文件: $cusFile2")

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            try {
                val cusFile3 = File(externalStoragePublicDirectoryMusic, "world.text")
                cusFile3.createNewFile()
                Log.d(TAG, "外部共有存储自定义文件: $cusFile3")
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            for (str in permissions) {
                if (checkCallingOrSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(permissions, 100)
                    return
                }
            }
            externalStoragePrivate()
            externalStoragePublic()
        } else {
            externalStoragePrivate()
            externalStoragePublic()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (it in permissions) {
            Log.d(TAG, "permissions: $it")
            externalStoragePrivate()
            externalStoragePublic()
        }
        for (it in grantResults) {
            Log.d(TAG, "grantResults: $it")
        }
    }

    private fun writeDataByInternalStorage(data: ByteArray, name: String, mode: Int) {
        openFileOutput(name, mode).use {
            it.write(data)
        }
    }

    private fun readDataByInternalStorage(name: String): String {
        val bas = ByteArrayOutputStream()
        bas.use {
            openFileInput(name).use {
                val buff = ByteArray(1024)
                var len = it.read(buff)
                while (len > 0) {
                    bas.write(buff, 0, len)
                    len = it.read(buff)
                }
                return bas.toString()
            }
        }
    }

    /* Checks if external storage is available for read and write */
    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    /* Checks if external storage is available to at least read */
    fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }

}