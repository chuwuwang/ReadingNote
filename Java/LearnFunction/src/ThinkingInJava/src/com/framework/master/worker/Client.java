package com.framework.master.worker;

import java.time.Duration;
import java.time.Instant;

public class Client {

	public static void main(String[] args) {
		Master master = new Master(new Worker(), 10);
		for (int i = 0; i < 100; i++) {
			Task task = new Task();
			task.setId(i);
			task.setName("task : " + i);
			task.setPrice(i * 2);
			master.submit(task);
		}
		master.execute();

		while (true) {
			if (master.isComplete()) {
				Instant start = Instant.now();
				int result = master.getResult();
				Instant end = Instant.now();
				System.out.println("result : " + result + " time : " + Duration.between(start, end));
				break;
			}
		}
	}

}
