package com.zhou.jvm_07;

/**
 * 非主动使用类字段演示
 */
public class NotInitialization {

	public static void main(String[] args) {
		// System.out.println(SubClass.value);
		SuperClass[] sca = new SuperClass[10];
		System.out.println(sca.length);
	}

}

/**
 * 被动使用类字段演示一： 通过子类引用父类的静态字段，不会导致子类初始化
 */
class SuperClass {
	static {
		System.out.println("SuperClass init！");
	}
	public static int value = 123;
}

class SubClass extends SuperClass {
	static {
		System.out.println("SubClass init！");
	}
}
