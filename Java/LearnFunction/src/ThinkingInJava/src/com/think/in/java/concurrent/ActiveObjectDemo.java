package com.think.in.java.concurrent;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ActiveObjectDemo {

	private ExecutorService exec = Executors.newSingleThreadExecutor();
	private Random random = new Random(47);

	private void pause(int factor) {
		try {
			TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(factor));
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("sleep() interrupted");
		}
	}

	public Future<Integer> calculateInteger(final int x, final int y) {
		return exec.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("starting integer " + x + " + " + y);
				pause(2000);
				return x + y;
			}

		});
	}

	public Future<Float> calculateFloat(final float x, final float y) {
		return exec.submit(new Callable<Float>() {

			@Override
			public Float call() throws Exception {
				System.out.println("starting float " + x + " + " + y);
				pause(500);
				return x + y;
			}

		});
	}

	public void shutdown() {
		exec.shutdown();
	}

	public static void main(String[] args) {
		ActiveObjectDemo d1 = new ActiveObjectDemo();
		List<Future<?>> results = new CopyOnWriteArrayList<>();
		for (float i = 0.0f; i < 1.0f; i += 0.2f) {
			results.add(d1.calculateFloat(i, i));
		}
		for (int i = 0; i < 5; i++) {
			results.add(d1.calculateInteger(i, i));
		}
		System.out.println("all asynch calls made");
		while (results.size() > 0) {
			for (Future<?> future : results) {
				if (future.isDone()) {
					try {
						System.out.println(future.get());
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					results.remove(future);
				}
			}
		}
		d1.shutdown();
	}

}
