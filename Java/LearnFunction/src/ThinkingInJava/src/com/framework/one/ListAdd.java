package com.framework.one;

import java.util.ArrayList;
import java.util.List;

public class ListAdd {

	// volatile�ؼ��ֵ�����
	private volatile static List<String> list = new ArrayList<>();

	public void add() {
		list.add("bjsxt");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		final ListAdd list = new ListAdd();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						list.add();
						System.out.println("��ǰ�̣߳�" + Thread.currentThread().getName() + "�����һ��Ԫ��..");
						Thread.sleep(500);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}, "t1");

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (list.size() == 5) {
						System.out.println("��ǰ�߳��յ�֪ͨ��" + Thread.currentThread().getName() + " list size = 5 �߳�ֹͣ..");
						throw new RuntimeException();
					}
				}
			}

		}, "t2");

		t1.start();
		t2.start();
	}

}
