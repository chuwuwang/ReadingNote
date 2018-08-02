package com.zhou.chat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhou.chat.entity.User;

public class UserModel {

	private static UserModel sInstance = new UserModel();
	
	private List<User> mUserList;
	private Map<String, User> mUserMap;

	private UserModel() {
		mUserList = new ArrayList<User>();
		mUserMap= new HashMap<String, User>();
	}

	public static UserModel getInstance() {
		return sInstance;
	}
	
	public void saveUser(User user) {
		User u = mUserMap.get(user.getName());
		if (u == null) {
			mUserList.add(user);
			mUserMap.put(user.getName(), user);
		}
	}
	
	public List<User> getUserList() {
		return mUserList;
	}
	
	public void clear() {
		mUserList.clear();
		mUserMap.clear();
	}

}
