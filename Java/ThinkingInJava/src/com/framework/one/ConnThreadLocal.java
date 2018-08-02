package com.framework.one;

public class ConnThreadLocal {

	public static ThreadLocal<String> th = new ThreadLocal<>();

	public void setValue(String value) {
		th.set(value);
	}

	public void getValue() {
		System.out.println(Thread.currentThread().getName() + " : " + th.get());
	}

	public static void main(String[] args) {
		final ConnThreadLocal connThreadLocal = new ConnThreadLocal();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				connThreadLocal.setValue("zhou");
				connThreadLocal.getValue();
			}

		}, "t1");
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				connThreadLocal.setValue("ling");
				connThreadLocal.getValue();
			}

		}, "t2");
		t1.start();
		t2.start();
	}

}
