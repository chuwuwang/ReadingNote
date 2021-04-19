package com.nsz.kotlin.thread;

import java.util.LinkedList;

public class Producer extends Thread {

    private LinkedList<Integer> sharedList;

    public Producer(LinkedList<Integer> queue) {
        sharedList = queue;
    }

    @Override
    public void run() {
        int count = 0;
        for (int i = 0; i < 20; i++) {
            synchronized (sharedList) {
                if (sharedList.size() >= 5) {
                    System.out.println("产能过剩");
                    try {
                        sharedList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    count++;
                    sharedList.add(count);
                    System.out.println("生产产品: " + count);
                    sharedList.notify();
                }
            }
        }
    }

}