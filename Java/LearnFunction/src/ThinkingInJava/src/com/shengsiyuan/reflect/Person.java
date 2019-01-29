package com.shengsiyuan.reflect;

public class Person {

	public String name;

	public Person() {
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("unused")
	private void sayHello(String friendName) {
		System.out.println(name + " say hello to " + friendName);
	}

	public void showMyName() {
		System.out.println("My name is " + name);
	}

}