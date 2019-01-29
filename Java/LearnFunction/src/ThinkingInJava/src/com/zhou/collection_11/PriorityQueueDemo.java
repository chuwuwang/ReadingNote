package com.zhou.collection_11;

import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueDemo {

	public static void main(String[] args) {

		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
		Random random = new Random(47);
		for (int i = 0; i < 10; i++) {
			priorityQueue.offer(random.nextInt(i + 10));
		}
		QueueDemo.printQ(priorityQueue);

	}

}
