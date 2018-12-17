package com.zhou.java8;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

@SuppressWarnings("unused")
public class TestMethodRef {

	@Test
	public void test1() {
		new ConcurrentGreeter().greet();

		Runnable r = () -> System.out.println("hello world");

		Consumer<String> consumer = System.out::println;

		BinaryOperator<String> binaryOperator = (x, y) -> {
			return x + y;
		};
		BinaryOperator<Long> bo = (x, y) -> x + y;

		Function<String, String> function = (n) -> new String(n);
		function = String::new;

		Supplier<String> supplier = () -> new String();
		supplier = String::new;
	}

	Runnable r = new Runnable() {

		@Override
		public void run() {
			System.out.println("hello world");
		}
	};

}

@SuppressWarnings("unused")
class Greeter {

	public void greet() {
		System.out.println("hello world");
		BinaryOperator<String> binaryOperator = (String x, String y) -> {
			return x + y;
		};
	}

}

class ConcurrentGreeter extends Greeter {

	@Override
	public void greet() {
		Thread t = new Thread(super::greet);
		t.start();
	}

}
