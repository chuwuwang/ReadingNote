package com.think.in.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("unused")
public class EventChecker implements Runnable {

	private IntGenerator generator;
	private final int id;

	public EventChecker(IntGenerator generator, int id) {
		super();
		this.generator = generator;
		this.id = id;
	}

	@Override
	public void run() {
		while (!generator.isCanceled()) {
			int val = generator.next();
			if (val % 2 != 0) {
				System.out.println(val + " not even!");
				generator.cancel();
			}
		}
	}

	public static void test(IntGenerator gp, int count) {
		System.out.println("press control start");
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			exec.execute(new EventChecker(gp, i));
		}
		exec.shutdown();
	}

	public static void test(IntGenerator gp) {
		test(gp, 10);
	}

}
