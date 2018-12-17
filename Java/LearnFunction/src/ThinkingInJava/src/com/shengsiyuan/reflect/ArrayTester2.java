package com.shengsiyuan.reflect;

import java.lang.reflect.Array;

public class ArrayTester2 {

	public static void main(String[] args) {

		int[] dim = new int[] { 5, 10, 6 };

		Object object = Array.newInstance(Integer.TYPE, dim);
		System.out.println(object);

		Object obj = Array.newInstance(int.class);
		System.out.println(obj);

	}

}
