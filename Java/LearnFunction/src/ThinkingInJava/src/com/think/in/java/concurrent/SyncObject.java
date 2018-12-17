package com.think.in.java.concurrent;

public class SyncObject {

	private Object syncObject = new Object();

	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}

	public void g() {
		synchronized (syncObject) {
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}

	public static void main(String[] args) {
		final SyncObject object = new SyncObject();
		new Thread() {

			@Override
			public void run() {
				object.f();
			}

		}.start();
		object.g();
	}

}
