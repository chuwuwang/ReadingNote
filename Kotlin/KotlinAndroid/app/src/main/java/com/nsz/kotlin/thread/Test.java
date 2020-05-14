package com.nsz.kotlin.thread;

import java.util.LinkedList;

public class Test {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        Producer producer = new Producer(linkedList);
        Consumer consumer = new Consumer(linkedList);
        producer.start();
        consumer.start();
    }

}