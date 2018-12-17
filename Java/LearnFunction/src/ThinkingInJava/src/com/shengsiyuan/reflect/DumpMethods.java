package com.shengsiyuan.reflect;

import java.lang.reflect.Method;

public class DumpMethods {

	public static void main(String[] args) throws Exception {
		Class<?> clazz = Class.forName("java.lang.String");
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method);
		}
	}

}
