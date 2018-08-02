package com.framework.one;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {

	private final LinkedList<Object> linkedList = new LinkedList<>();
	private final AtomicInteger count = new AtomicInteger(0);

	private int maxSize;
	private int minSize;

	private final Object lock = new Object();

	public MyQueue(int max) {
		this.maxSize = max;
	}

	public void put(Object obj) {
		synchronized (lock) {
			while (count.get() == maxSize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			linkedList.add(obj);
			count.getAndIncrement();
			System.out.println("Ԫ�� " + obj + " ����� ");
			lock.notify();
		}
	}

	public Object take() {
		Object temp = null;
		synchronized (lock) {
			while (count.get() == minSize) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			count.getAndDecrement();
			temp = linkedList.removeFirst();
			System.out.println("Ԫ�� " + temp + " ������ ");
			lock.notify();
		}
		return temp;
	}

	public int size() {
		return count.get();
	}

	public static void main(String[] args) {
		final MyQueue m = new MyQueue(5);
		m.put("a");
		m.put("b");
		m.put("c");
		m.put("d");
		m.put("e");
		System.out.println("��ǰԪ�ظ�����" + m.size());

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				m.put("h");
				m.put("i");
			}

		}, "t1");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					Object o1 = m.take();
					System.out.println("��ȡ�ߵ�Ԫ��Ϊ��" + o1);
					Thread.sleep(1000);
					Object o2 = m.take();
					System.out.println("��ȡ�ߵ�Ԫ��Ϊ��" + o2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}, "t2");

		t1.start();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
	}

}
