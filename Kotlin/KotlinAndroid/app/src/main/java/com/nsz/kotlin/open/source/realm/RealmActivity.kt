package com.nsz.kotlin.open.source.realm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_open_source_realm.*

class RealmActivity : AppCompatActivity() {

    private val realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_source_realm)
        initView()
    }

    private val dog = Dog()
    private fun initView() {
        mb_ok.setOnClickListener {
            val string = edit_input.text.toString()
            dog.name = string
            realm.executeTransaction {
                it.copyToRealmOrUpdate(dog)
            }
            realm.executeTransaction {
                val results = it.where(Dog::class.java).findAll()
                val stringBuilder = StringBuilder()
                for (item in results) {
                    stringBuilder.append(item.name)
                    stringBuilder.append(" -> ")
                }
                tv_data.text = stringBuilder.toString()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

}