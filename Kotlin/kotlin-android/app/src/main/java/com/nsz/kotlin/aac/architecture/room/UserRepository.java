package com.nsz.kotlin.aac.architecture.room;

import android.content.Context;

import com.nsz.kotlin.ux.common.executors.AppExecutors;

import java.lang.ref.WeakReference;
import java.util.UUID;

public class UserRepository {

    private final AppExecutors mAppExecutors;
    private final UserDatabase mUserDatabase;

    private User mCachedUser;

    public UserRepository(Context context, AppExecutors appExecutors) {
        mAppExecutors = appExecutors;
        mUserDatabase = UserDatabase.getInstance(context);
    }

    void getUser(LoadUserCallback callback) {
        final WeakReference<LoadUserCallback> loadUserCallback = new WeakReference<>(callback);

        // request the user on the I/O thread
        mAppExecutors.diskIO().execute(
                () -> {
                    final User user = mUserDatabase.userDao().getUser();
                    // notify on the main thread
                    mAppExecutors.mainThread().execute(
                            () -> {
                                final LoadUserCallback userCallback = loadUserCallback.get();
                                if (userCallback == null) {
                                    return;
                                }
                                if (user == null) {
                                    userCallback.onDataNotAvailable();
                                } else {
                                    mCachedUser = user;
                                    userCallback.onUserLoaded(mCachedUser);
                                }
                            }
                    );
                }
        );
    }

    void updateUserName(String userName, LoadUserCallback callback) {
        final WeakReference<LoadUserCallback> loadUserCallback = new WeakReference<>(callback);

        User user;
        if (mCachedUser != null) {
            user = mCachedUser;
            user.setFirstName(userName);
        } else {
            String uuid = UUID.randomUUID().toString();
            user = new User();
            user.setUId(uuid);
            user.setFirstName(userName);
        }

        // update the user on the I/O thread
        mAppExecutors.diskIO().execute(
                () -> {
                    mUserDatabase.userDao().insert(user);
                    mCachedUser = user;
                    // notify on the main thread
                    mAppExecutors.mainThread().execute(
                            () -> {
                                LoadUserCallback userCallback = loadUserCallback.get();
                                if (userCallback != null) {
                                    userCallback.onUserLoaded(user);
                                }
                            }
                    );
                }
        );
    }

}