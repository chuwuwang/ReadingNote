package com.think.in.java.concurrent;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableDemo {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			results.add(service.submit(new TaskWithResult(i)));
		}
		for (Future<String> fs : results) {
			try {
				System.err.println("Hello");
				System.out.println(fs.get());
				System.err.println("World");
			} catch (InterruptedException e) {
				System.out.println(e);
				return;
			} catch (ExecutionException e) {
				System.out.println(e);
			} finally {
				service.shutdown();
			}
		}
		System.out.printf("%1$5d\n", 22);
		System.out.printf("%1$5s\n", "111");
		System.out.printf("%1$5x\n", 27);
		System.out.printf("%.8f\n", 17.5);
		System.out.printf("%1$5d\n%2$06d\n", 22, 44);
		System.out.printf("%1$05d\n%2$06x\n", 22, 44);
	}

}

class TaskWithResult implements Callable<String> {

	private int id;

	public TaskWithResult(int id) {
		super();
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		System.err.println("call");
		TimeUnit.SECONDS.sleep(2);
		return "Result of TaskWithResult " + id;
	}

}
