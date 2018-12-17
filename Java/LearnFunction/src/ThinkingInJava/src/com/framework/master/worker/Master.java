package com.framework.master.worker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

	// ��װ����ļ���
	private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<>();

	// ʹ��HashMapȥ���b����worker����
	private HashMap<String, Thread> workerMap = new HashMap<>();

	// ʹ��һ��������װÿһ��worker����ִ������Ľ��
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

	public Master(Worker worker, int count) {
		worker.setWorkerQueue(workQueue);
		worker.setResultMap(resultMap);
		for (int i = 0; i < count; i++) {
			workerMap.put("�ӽڵ�" + Integer.toString(i), new Thread(worker));
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
