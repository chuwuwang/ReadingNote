package com.think.in.java.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ToastOMatic {

	public static void main(String[] args) throws Exception {
		ToastQueue dryQueue = new ToastQueue(), butteredQueue = new ToastQueue(), finishedQueue = new ToastQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, butteredQueue));
		exec.execute(new Jammer(butteredQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}

}

class Toast {

	public enum Status {
		DRY, BUTTERED, JAMMED
	};

	private Status status = Status.DRY;
	private final int id;

	public Toast(int id) {
		super();
		this.id = id;
	}

	public void butter() {
		status = Status.BUTTERED;
	}

	public void jam() {
		status = Status.JAMMED;
	}

	public int getID() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Toast " + id + " : " + status;
	}

}

class ToastQueue extends LinkedBlockingQueue<Toast> {
	private static final long serialVersionUID = -2400582870923742463L;
}

class Toaster implements Runnable {

	private ToastQueue toastQueue;
	private int count = 0;
	private Random random = new Random(47);

	public Toaster(ToastQueue toastQueue) {
		super();
		this.toastQueue = toastQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
				Toast t = new Toast(count++);
				System.out.println("Toaster >>> " + t);
				toastQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.err.println("Toaster Interrupted");
		}
		System.out.println("Toaster off");
	}

}

class Butterer implements Runnable {

	private ToastQueue dryQueue;
	private ToastQueue butteredQueue;

	public Butterer(ToastQueue dryQueue, ToastQueue butteredQueue) {
		super();
		this.dryQueue = dryQueue;
		this.butteredQueue = butteredQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = dryQueue.take();
				t.butter();
				System.out.println("Butterer >>> " + t);
				butteredQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.err.println("Butterer Interrupted");
		}
		System.out.println("Butterer off");
	}

}

class Jammer implements Runnable {

	private ToastQueue butteredQueue;
	private ToastQueue finishQueue;

	public Jammer(ToastQueue butteredQueue, ToastQueue finishQueue) {
		super();
		this.butteredQueue = butteredQueue;
		this.finishQueue = finishQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = butteredQueue.take();
				t.jam();
				System.out.println("Jammer >>> " + t);
				finishQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.err.println("Jammer Interrupted");
		}
		System.out.println("Jammer off");
	}

}

class Eater implements Runnable {

	private ToastQueue finishQueue;
	private int counter = 0;

	public Eater(ToastQueue finishQueue) {
		super();
		this.finishQueue = finishQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = finishQueue.take();
				if (t.getID() != counter++ || t.getStatus() != Toast.Status.JAMMED) {
					System.out.println(">>> error:" + t);
					System.exit(1);
				} else {
					System.out.println("Chomp! " + t);
				}
			}
		} catch (InterruptedException e) {
			System.err.println("Eater Interrupted");
		}
		System.out.println("Eater off");
	}

}
