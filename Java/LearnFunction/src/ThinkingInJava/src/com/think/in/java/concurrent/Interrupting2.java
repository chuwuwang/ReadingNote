package com.think.in.java.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Interrupting2 {

	public static void main(String[] args) {
		BlockedMutex blockedMutex = new BlockedMutex();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				blockedMutex.a();
				blockedMutex.f();
			}
		}).start();
	}

}

class BlockedMutex {

	private Lock lock = new ReentrantLock();

	public BlockedMutex() {
		System.out.println("BlockedMutex start");
		lock.lock();
		System.out.println("BlockedMutex end");
	}

	public void a() {
		lock.lock();
		try {
			System.out.println("a start");
			boolean b = lock.tryLock();
			System.out.println("a end " + b);
		} finally {
			System.out.println("try finally");
		}
	}

	public void f() {
		try {
			System.out.println("f start");
			lock.lockInterruptibly();
			System.out.println("f wait");
		} catch (InterruptedException e) {
			System.out.println("f InterruptedException");
			e.printStackTrace();
		}
		System.out.println("f end");
	}

}