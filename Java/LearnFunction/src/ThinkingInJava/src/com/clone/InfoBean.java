package com.clone;

public class InfoBean implements Cloneable {

	public String name;
	public int age;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
