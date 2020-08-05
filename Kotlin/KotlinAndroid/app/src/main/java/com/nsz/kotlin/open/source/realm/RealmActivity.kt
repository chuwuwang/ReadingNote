package com.nsz.kotlin.open.source.realm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.ux.common.CommonLog
import io.realm.Realm

class RealmActivity : AppCompatActivity() {

    private val realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private val dog = Dog()
    private fun initView() {
        dog.age = 2
        dog.name = "you"
        realm.executeTransaction { it.copyToRealmOrUpdate(dog) }

        realm.executeTransaction {
            val d = it.where(Dog::class.java).equalTo("name", "you").findFirst()
            d.age = 5
        }

        CommonLog.d("dog age: " + dog.age)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

}