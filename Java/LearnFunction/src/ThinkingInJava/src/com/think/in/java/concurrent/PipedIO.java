package com.think.in.java.concurrent;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PipedIO {

	public static void main(String[] args) throws IOException, InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Sender sender = new Sender();
		Receiver receiver1 = new Receiver(sender, "one");
		exec.execute(sender);
		exec.execute(receiver1);
		TimeUnit.SECONDS.sleep(10);
		exec.shutdownNow();
	}

}

class Sender implements Runnable {

	private Random rand = new Random(47);
	private PipedWriter out = new PipedWriter();

	public PipedWriter getPipedWriter() {
		return out;
	}

	@Override
	public void run() {
		try {
			while (true) {
				for (char i = 'A'; i < 'z'; i++) {
					out.write(i);
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				}
			}
		} catch (IOException e) {
			System.err.println("Sender IO");
		} catch (InterruptedException e) {
			System.err.println("Sender Interrupted");
		}
	}

}

class Receiver implements Runnable {

	private PipedReader in;
	private String name;

	public Receiver(Sender sender, String name) throws IOException {
		super();
		in = new PipedReader(sender.getPipedWriter());
		this.name = name;
	}

	@Override
	public void run() {
		try {
			while (true) {
				System.out.println(name + " read >>> " + (char) in.read() + ", ");
			}
		} catch (IOException e) {
			System.err.println("Receiver " + name + " IO");
		}
	}

}
