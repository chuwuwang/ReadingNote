package com.think.in.java_15;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LatentRefecttion {
		
	public static void main(String[] args) {
		CommunicateReflectively.perform(new SmartDog());
		CommunicateReflectively.perform(new Mime());
	}
	
}

class Mime {

	public void walkAgainstTheWind() {
		
	}

	public void sit() {
		System.out.println("Pretending to sit");
	}

	public void pushInvisibleWalls() {

	}

	public String toString() {
		return "Mime";
	}

}

class SmartDog {

	public void speak() {
		System.out.println("Woof");
	}

	public void sit() {
		System.out.println("sitting");
	}

	public void reproduce() {

	}

}

class CommunicateReflectively {

	public static void perform(Object spearker) {
		Class<? extends Object> spkr = spearker.getClass();
		try {
			try {
				Method speak = spkr.getMethod("speak");
				speak.invoke(spearker, new Object[]{});
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			try {
				Method sit = spkr.getMethod("sit");
				sit.invoke(spearker, new Object[]{});
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

}
