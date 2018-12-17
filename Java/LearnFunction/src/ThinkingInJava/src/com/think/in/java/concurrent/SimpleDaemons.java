package com.think.in.java.concurrent;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("sleep() Interrupted");
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread daemons = new Thread(new SimpleDaemons());
			daemons.setDaemon(true);
			daemons.start();
		}
		System.out.println("All daemon started");
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
