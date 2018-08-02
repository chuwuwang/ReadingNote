package com.zhou.chat.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.zhou.chat.entity.User;

public class DiskFileUtils {

	public static final String FILE_DIR = "repertory/";
	public static final String FILE_NAME = "user_repertory.key";

	public static void writeUserRepertory2Disk(List<User> user) {
		try {
			File dir = new File(FILE_DIR);
			if (!dir.exists() || !dir.isDirectory()) {
				dir.mkdirs();
			}
			File file = new File(dir, FILE_NAME);
			FileOutputStream fos = new FileOutputStream(file, true);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(user);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<User> readUserRepertory2Disk() {
		try {
			// 判断是否有sd卡
			File dir = new File(FILE_DIR);
			if (!dir.exists() || !dir.isDirectory()) {
				dir.mkdirs();
			}
			File file = new File(dir, FILE_NAME);
			if (!file.exists() || !file.isFile())
				return null;
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			List<User> userList = (List<User>) ois.readObject();
			fis.close();
			ois.close();
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
