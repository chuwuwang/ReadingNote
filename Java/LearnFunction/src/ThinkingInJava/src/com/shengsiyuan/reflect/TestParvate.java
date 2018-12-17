package com.shengsiyuan.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestParvate {

	public static void main(String[] args) throws Exception {
		Private p = new Private();
		Class<? extends Private> classType = p.getClass();
		Class<?> superclass = classType.getSuperclass();
		System.out.println(superclass);

		Class<?>[] interfaces = classType.getInterfaces();
		for (Class<?> clazz : interfaces) {
			System.out.println(clazz);
		}

		Method method = classType.getDeclaredMethod("sayHello", new Class[] { String.class });
		method.setAccessible(true);
		String str = (String) method.invoke(p, new Object[] { "leeshenzhou" });
		System.out.println(str);
		int modifiers = method.getModifiers();
		System.out.println(modifiers);

		Field field = classType.getDeclaredField("name");
		field.setAccessible(true);
		field.set(p, "lisi");
		System.out.println(p.getName());
	}

}

class Private {

	private String name = "zhangsan";

	public String getName() {
		return name;
	}

	@SuppressWarnings("unused")
	private String sayHello(String name) {
		return "hello " + name;
	}

}