package com.zhou.java8;

public interface Color {
	
	int getColor();
	
	default String getName() {
		return "red";
	}
	
}

interface Pan {
	
	void show();
	
	default String getName() {
		return "pan";
	}
}

class Car implements Color, Pan {

	@Override
	public void show() {
		
	}

	@Override
	public int getColor() {
		return 0;
	}

	@Override
	public String getName() {
		return Color.super.getName();
	}
	
}
