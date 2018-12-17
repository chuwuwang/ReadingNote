package com.shengsiyuan.reflect;

import java.lang.reflect.Array;

public class ArrayTester1 {
	
	public static void main(String[] args) throws Exception {
		Class<?> classType = Class.forName("java.lang.String");
		Object array = Array.newInstance(classType, 10);
		
		Array.set(array, 5, "hello");
		String str = (String) Array.get(array, 5);
		System.out.println(str);
	}
	
}
