package com.framework.master.worker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {

	private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<>();
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

	public void setWorkerQueue(ConcurrentLinkedQueue<Task> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	@Override
	public void run() {
		while (true) {
			Task task = workQueue.poll();
			if (task == null) {
				break;
			}
			Object result = handle(task);
			resultMap.put(Integer.toString(task.getId()), result);
		}
	}

	private Object handle(Task task) {
		Object obj = null;
		try {
			Thread.sleep(500);
			obj = task.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
