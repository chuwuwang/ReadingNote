package com.framework.master.worker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

	// 承装任务的集合
	private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<>();

	// 使用HashMap去承裝所有worker對象
	private HashMap<String, Thread> workerMap = new HashMap<>();

	// 使用一个容器承装每一个worker并发执行任务的结果
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

	public Master(Worker worker, int count) {
		worker.setWorkerQueue(workQueue);
		worker.setResultMap(resultMap);
		for (int i = 0; i < count; i++) {
			workerMap.put("子节点" + Integer.toString(i), new Thread(worker));
		}
	}

	public void submit(Task task) {
		workQueue.add(task);
	}

	public void execute() {
		for (Map.Entry<String, Thread> me : workerMap.entrySet()) {
			me.getValue().start();
		}
	}

	public boolean isComplete() {
		for (Map.Entry<String, Thread> me : workerMap.entrySet()) {
			if (me.getValue().getState() != Thread.State.TERMINATED) {
				return false;
			}
		}
		return true;
	}

	public int getResult() {
		int result = 0;
		for (Map.Entry<String, Object> me : resultMap.entrySet()) {
			result += (Integer) me.getValue();
		}
		return result;
	}

}
