package com.think.in.java.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Restaurant {

	List<Meal> meals = new ArrayList<>();
	Chef chef = new Chef(this);
	WaitPerson waitPerson = new WaitPerson(this);
	ExecutorService exec = Executors.newCachedThreadPool();

	public Restaurant() {
		exec.execute(waitPerson);
		exec.execute(chef);
	}

	public static void main(String[] args) {
		new Restaurant();
	}

}

class Meal {

	private final int orderNum;

	public Meal(int orderNum) {
		super();
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "Meal " + orderNum;
	}

}

class WaitPerson implements Runnable {

	private Restaurant restaurant;

	public WaitPerson(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meals.size() == 0) {
						wait();
					}
				}
				System.out.println("WaitPerson got " + restaurant.meals.size());
				synchronized (restaurant.chef) {
					restaurant.meals.remove(0);
					restaurant.chef.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(150);
			}
		} catch (InterruptedException e) {
			System.out.println("WaitPerson Interrupted");
		}
	}

}

class Chef implements Runnable {

	private Restaurant restaurant;
	private int count;

	public Chef(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meals.size() >= 10) {
						wait();
					}
				}
				if (++count == 10) {
					System.out.println("Out if food close");
					restaurant.exec.shutdownNow();
					return;
				}
				System.out.println("Order up");
				synchronized (restaurant.waitPerson) {
					restaurant.meals.add(new Meal(count));
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Chef Interrupted");
		}
	}

}