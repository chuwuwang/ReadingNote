package com.think.in.java.concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestBlockingQueues {
	
	static void getKey() {
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void getKey(String message) {
		System.out.println(message);
		getKey();
	}
	
	static void test(String msg, BlockingQueue<LiftOff> queue) {
		System.out.println(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
		Thread t = new Thread(runner);
		t.start();
		for (int i = 0; i < 5; i++) {
			runner.add(new LiftOff(5));
		}
		getKey("Press Enter");
		t.interrupt();
		System.out.println("finish");
	}
	
	public static void main(String[] args) {
		test("11111", new LinkedBlockingQueue<>());
	}
	
}

class LiftOffRunner implements Runnable {

	private BlockingQueue<LiftOff> rockets;
	
	public LiftOffRunner(BlockingQueue<LiftOff> rockets) {
		super();
		this.rockets = rockets;
	}
	
	public void add(LiftOff lo) {
		try {
			rockets.put(lo);
		} catch (InterruptedException e) {
			System.out.println("Interrupted during put()");
		}
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				LiftOff take = rockets.take();
				take.run();
			}
		} catch (InterruptedException e) {
			System.out.println("waking for take");
		}
		System.out.println("Exiting LiftOffRunner");
	}
	
}
