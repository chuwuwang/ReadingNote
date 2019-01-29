package com.zhou.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;

public class TestLambda {

	@Test
	public void test1() {
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
		TreeSet<Integer> ts = new TreeSet<>(com);
		System.err.println(ts.size());
	}

	@Test
	public void test2() {
		List<String> list = Arrays.asList("ccc", "aaa", "bbb", "ddd", "eee");
		list.stream().sorted().forEach(System.out::println);

		System.out.println("-------------------------");

	}

	private void op(Long l1, Long l2, MyFunction2<Long, Long> mf) {
		System.out.println(mf.getValue(l1, l2));
	}

	@Test
	public void test3() {
		op(100L, 200L, (x, y) -> x + y);
	}

}
