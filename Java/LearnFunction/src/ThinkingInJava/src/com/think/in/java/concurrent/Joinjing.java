package com.think.in.java.concurrent;

public class Joinjing {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Sleeper sleepy = new Sleeper("Sleepy", 1500);
		Sleeper grumpy = new Sleeper("grumpy", 1500);
		Joiner dopey = new Joiner("dopey", sleepy);
		Joiner doc = new Joiner("doc", grumpy);
		grumpy.interrupt();
	}

}

class Sleeper extends Thread {

	private int duration;

	public Sleeper(String name, int sleepTime) {
		super(name);
		duration = sleepTime;
		start();
	}

	@Override
	public void run() {
		super.run();
		try {
			sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Interrupted Exception");
			return;
		}
		System.out.println(getName() + " has awakened");
	}

}

class Joiner extends Thread {

	private Sleeper sleeper;

	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}

	@Override
	public void run() {
		super.run();
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getName() + " join completed");
	}

}
