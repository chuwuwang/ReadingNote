package com.shengsiyuan.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Client {

	public static void main(String[] args) throws Exception {

		// getClassObj();

		// getObjectOne();
		// getObjectTwo();

		// getDeclaredMethods();

		// getDeclaredFields();

		getSuperClass();

		getInterface();

	}

	/**
	 * 获取对象的父类
	 */
	public static void getSuperClass() {
		Class<?> superClass = Student.class.getSuperclass();
		while (superClass != null) {
			System.out.println("superClass = " + superClass.getName());
			// 循环获取上一层父类（如果存在）,至少存在一层java.lang.Object
			superClass = superClass.getSuperclass();
		}
	}

	/**
	 * 获取对象实现的接口
	 */
	public static void getInterface() {
		// 获取该类实现的所有接口
		Class<?>[] interfaces = Student.class.getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			System.out.println("interfaces[" + i + "] = " + interfaces[i].getName());
		}
	}

	/**
	 * 反射获取类的属性
	 */
	public static void getDeclaredFields() throws Exception {
		// 获取类的Class对象
		Class<?> clz = Student.class;
		Student student = (Student) clz.newInstance();
		student.setAdd("ShangHai");
		student.setGrade(9);
		student.setName("leeshenzhou");

		// 获取当前类和父类的所有属性
		Field[] fields = clz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println("fields[" + i + "] = " + fields[i].getName());
		}

		// 获取当前类的某个属性
		Field field = clz.getDeclaredField("grade");
		// 获取属性值
		System.out.println("student.grade before = " + field.get(student));

		// 设置属性值
		field.set(student, 20);

		System.out.println("student.grade after = " + field.get(student));
	}

	/**
	 * 反射获取类的方法
	 */
	public static void getDeclaredMethods() throws Exception {
		// 获取类的Class对象
		Class<?> clz = Student.class;
		Student student = (Student) clz.newInstance();
		student.setAdd("ShangHai");
		student.setGrade(9);
		student.setName("leeshenzhou");

		// 获取到类中的所有方法(不包含从父类继承的方法)
		Method[] methods = clz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println("method[" + i + "] = " + methods[i].getName());
		}

		// 获取类中的某个方法
		Method method = clz.getDeclaredMethod("setAdd", new Class[] { String.class });
		// 判断是否是public方法
		System.out.println("method is public = " + Modifier.isPublic(method.getModifiers()));
		// 获取该方法的参数类型列表
		Class<?>[] paramTypes = method.getParameterTypes();
		for (int i = 0; i < paramTypes.length; i++) {
			System.out.println("paramTypes[" + i + "] = " + paramTypes[i].getName());
		}
		System.out.println("student.addr before= " + student.getAdd());

		// 执行该方法
		method.invoke(student, new Object[] { "Beijing" });

		System.out.println("student.addr after= " + student.getAdd());
	}

	/**
	 * 反射获取类的对象
	 */
	public static Object getObjectTwo() {
		try {
			// 获取类的Class对象
			Class<?> clz = Student.class;
			Student obj = (Student) clz.newInstance();
			obj.grade = 10;
			obj.name = "zhangsan";
			System.out.println(obj.toString());
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反射获取类的对象
	 */
	public static Object getObjectOne() {
		try {
			// 获取类的Class对象
			Class<?> clz = Student.class;
			// 获取类对象的Constructor
			Constructor<?> constructor = clz.getConstructor(int.class, String.class);
			// 在使用时取消 Java语言访问检查，提升反射性能
			constructor.setAccessible(true);
			// 通过 Constructor 来创建对象
			Object obj = constructor.newInstance(9, "leeshenzhou");
			System.out.println(obj.toString());
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取Class对象的三种方式
	 */
	public static Class<?> getClassObj() {
		// 根据类名获取Class对象
		Class<?> clazz1 = Student.class;
		System.out.println(clazz1);
		System.out.println("----------------------------");

		// 根据对象获取Class对象
		Student people = new Student();
		Class<?> clazz2 = people.getClass();
		System.out.println(clazz2);
		System.out.println("----------------------------");

		// 根据完整类名获取Class对象
		try {
			Class<?> clazz3 = Class.forName("com.shengsiyuan.reflect.Student");
			System.out.println(clazz3);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clazz1;
	}

}
