package com.think.in.java.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalVariableHolder {

	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {

		private Random random = new Random(47);

		@Override
		protected synchronized Integer initialValue() {
			return random.nextInt(10000);
		}

	};

	public static void increment() {
		value.set(value.get() + 1);
	}

	public static int get() {
		return value.get();
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Accessor(i));
		}
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		exec.shutdown();
	}

}

class Accessor implements Runnable {

	private final int id;

	public Accessor(int id) {
		super();
		this.id = id;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}

	@Override
	public String toString() {
		return "Accessor [id=" + id + " " + ThreadLocalVariableHolder.get() + "]";
	}

}
