package com.nsz.kotlin.thread;

import java.util.LinkedList;

public class Consumer extends Thread {

    private LinkedList<Integer> sharedList;

    public Consumer(LinkedList<Integer> queue) {
        sharedList = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (sharedList) {
                if (sharedList.size() == 0) {
                    System.out.println("没有东西可以消费了");
                    try {
                        sharedList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Integer ps = sharedList.poll();
                    System.out.println("消费产品: " + ps);
                    sharedList.notify();
                }
            }
        }
    }

}