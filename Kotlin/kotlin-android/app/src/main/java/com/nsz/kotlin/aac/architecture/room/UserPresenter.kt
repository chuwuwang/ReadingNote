package com.nsz.kotlin.aac.architecture.room

import android.content.Context
import com.nsz.kotlin.ux.common.executors.AppExecutors

class UserPresenter(var context: Context, var userView: UserView) {

    private val userRepository by lazy {
        val appExecutors = AppExecutors()
        UserRepository(context, appExecutors)
    }

    fun start() {
        userRepository.getUser(loadUserCallback)
    }

    fun stop() {

    }

    private val loadUserCallback = createLoadUserCallback()

    fun updateUserName(userName: String) {
        userRepository.updateUserName(userName, loadUserCallback)
    }

    private fun createLoadUserCallback(): LoadUserCallback {
        return object : LoadUserCallback {

            override fun onUserLoaded(user: User) {
                userView.showUserName(user.firstName)
            }

            override fun onDataNotAvailable() {
                userView.hideUserName()
            }

        }
    }

}