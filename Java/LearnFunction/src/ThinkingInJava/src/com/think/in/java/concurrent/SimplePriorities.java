package com.think.in.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {

	private int countDown = 5;
	private volatile double d;
	private int prioroty;

	public SimplePriorities(int prioroty) {
		super();
		this.prioroty = prioroty;
	}
	
	public double getD() {
		return d;
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority(prioroty);
		while (true) {
			for (int i = 0; i < 100000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0) {
					Thread.yield();
				}
			}
			System.out.println(this);
			if (--countDown == 0) {
				return;
			}
		}
	}

	@Override
	public String toString() {
		return Thread.currentThread() + " : " + countDown;
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			service.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		}
		service.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		service.shutdown();
	}

}
