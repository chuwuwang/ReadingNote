package com.nsz.kotlin.storage

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.storage.StorageManager
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import java.io.InputStream

class SAFActivity : AppCompatActivity() {

    // https://blog.csdn.net/GRY_YJ/article/details/105951089
    // https://www.jianshu.com/p/d5573e312bb8
    // https://blog.csdn.net/hyc1988107/article/details/83825237/?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242

    companion object {
        private const val TAG = "SAFActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_saf)
        initView()

    }

    private fun initView() {
        operateSDCardStorage()
        val inputStream = resources.assets.open("test.txt")
        saveFileToPublicDirectory(inputStream)
        openTree()
    }

    private fun openDocument() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        // intent.type = "*/*"
        intent.type = "text/*"
        // 是否支持多选 默认不支持
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
        startActivityForResult(intent, 100)
    }

    private fun openTree() {
        val uri: Uri = Uri.parse("content://com.android.external" + "storage.documents/document/primary:Download")
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri)
        }
        startActivityForResult(intent, 101)
    }

    private fun createDocument() {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "text/*"
        intent.putExtra(Intent.EXTRA_TITLE, "123.txt")
        startActivityForResult(intent, 102)
    }

    private fun saveFileToPublicDirectory(inputStream: InputStream) {
        val values = ContentValues()
        val currentTimeMillis = System.currentTimeMillis()
        values.put(MediaStore.Images.Media.TITLE, "ktx_$currentTimeMillis")
        values.put(MediaStore.Downloads.DISPLAY_NAME, "ktx_$currentTimeMillis.txt")
        values.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + "/ktx")
        val contentUri = MediaStore.Downloads.EXTERNAL_CONTENT_URI
        contentResolver.insert(contentUri, values).let { uri ->
            if (uri != null) {
                try {
                    val outputStream = contentResolver.openOutputStream(uri)
                    if (outputStream != null) {
                        outputStream.use { ops ->
                            val buffer = ByteArray(2048)
                            var len: Int = inputStream.read(buffer)
                            while (len != -1) {
                                ops.write(buffer, 0, len)
                                len = inputStream.read(buffer)
                            }
                        }
                    } else {

                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }

    private fun operateSDCardStorage() {
        val storageManager = getSystemService(Context.STORAGE_SERVICE) as StorageManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            val storageVolumes = storageManager.storageVolumes
            for (sv in storageVolumes) {
                Log.e(TAG, "storageVolume: $sv")
                if (sv.isRemovable && sv.state == Environment.MEDIA_MOUNTED) {
                    val intent = sv.createOpenDocumentTreeIntent()
                    startActivityForResult(intent, 1001)
                    return
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            val uri = data.data
            Log.e(TAG, "uri: $uri")
        }
        if (data != null && requestCode == 1001) {
            val uri = data.data
        } else if (data != null && requestCode == 1001) {
            val uri = data.data
            if (uri != null) {
                val flags = intent.flags and (Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                contentResolver.takePersistableUriPermission(uri, flags)
            }
        }
    }

}