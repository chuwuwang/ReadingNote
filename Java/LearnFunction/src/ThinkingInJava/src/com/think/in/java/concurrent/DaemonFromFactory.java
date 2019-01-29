package com.think.in.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DaemonFromFactory implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("InterruptedException");
		}
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 10; i++) {
			service.execute(new DaemonFromFactory());
		}
		System.out.println("add daemon started");
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
