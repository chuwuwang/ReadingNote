package com.nsz.kotlin.aac.architecture.room

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.nsz.kotlin.R
import kotlinx.android.synthetic.main.activity_aac_architecture_room.*

class RoomActivity : AppCompatActivity(), UserView {

    private val userPresenter by lazy {
        UserPresenter(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aac_architecture_room)
        initView()
    }

    private fun initView() {
        mb_update_user.setOnClickListener {
            val updateName = edit_user_name_input.text.toString()
            userPresenter.updateUserName(updateName)
        }
    }

    override fun onStart() {
        super.onStart()
        userPresenter.start()
    }

    override fun onStop() {
        super.onStop()
        userPresenter.stop()
    }

    override fun showUserName(userName: String) {
        tv_user_name.visibility = View.VISIBLE
        tv_user_name.text = userName
    }

    override fun hideUserName() {
        tv_user_name.visibility = View.INVISIBLE
    }

}