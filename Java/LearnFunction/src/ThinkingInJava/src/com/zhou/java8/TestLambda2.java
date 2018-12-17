package com.zhou.java8;

import java.util.function.Consumer;

import org.junit.Test;

public class TestLambda2 {

	@Test
	public void test1() {
		Runnable r1 = () -> System.out.println("Hello Lambda!");
		r1.run();
	}

	@Test
	public void test2() {
		Consumer<String> con = (x) -> System.out.println(x);
		con.accept("Hello World!");
	}

	@Test
	public void test3() {
		Integer num = operation(100, (x) -> x * x);
		System.out.println(num);
	}

	private Integer operation(Integer num, MyFunction mf) {
		return mf.getValue(num);
	}

}
