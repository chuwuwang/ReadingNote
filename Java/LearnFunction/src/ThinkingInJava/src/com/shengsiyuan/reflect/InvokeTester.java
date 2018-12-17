package com.shengsiyuan.reflect;

import java.lang.reflect.Method;

public class InvokeTester {

	public static void main(String[] args) throws Exception {
		Class<?> clazz = InvokeTester.class;
		Object obj = clazz.newInstance();
		Method addMethod = clazz.getMethod("add", new Class[] { int.class, int.class });
		Object result = addMethod.invoke(obj, new Object[] { 1, 2 });
		System.out.println(result);

		System.out.println("----------------------------");

		Method echoMethod = clazz.getMethod("echo", new Class[] { String.class });
		Object res = echoMethod.invoke(obj, new Object[] { "leeshenzhou" });
		System.out.println(res);
	}

	public int add(int arg0, int arg1) {
		return arg0 + arg1;
	}

	public String echo(String message) {
		return "echo:" + message;
	}

}
