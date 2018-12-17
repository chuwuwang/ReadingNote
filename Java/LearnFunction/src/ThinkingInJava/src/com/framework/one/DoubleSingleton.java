package com.framework.one;

public class DoubleSingleton {

	private static DoubleSingleton instance;

	private DoubleSingleton() {

	}

	public static DoubleSingleton getInstance() {
		if (instance == null) {
			try {
				// 模拟初始化对象的准备时间...
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (DoubleSingleton.class) {
				if (instance == null) {
					instance = new DoubleSingleton();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(DoubleSingleton.getInstance().hashCode());
			}

		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(DoubleSingleton.getInstance().hashCode());
			}

		});
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(DoubleSingleton.getInstance().hashCode());
			}

		});
		t1.start();
		t2.start();
		t3.start();
	}

}
