package com.zhou.adapter.clazz;

public class Adapter extends Adaptee implements Target {

	@Override
	public void normalMethod() {
		super.specificMethod();
	}

}
