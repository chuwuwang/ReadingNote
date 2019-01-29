package com.zhou.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * java8内置的四大核心函数式接口
 * 
 * @author Lee64
 *
 */
public class TestLambda3 {

	@Test
	public void test1() {
		happy(10000, (m) -> System.out.println(m));
	}

	private void happy(double money, Consumer<Double> con) {
		con.accept(money);
	}

	@Test
	public void test2() {
		List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
		int size = numList.size();
		for (int i = 0; i < size; i++) {
			System.out.println(numList.get(i));
		}
	}

	private List<Integer> getNumList(int num, Supplier<Integer> sup) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			Integer n = sup.get();
			list.add(n);
		}
		return list;
	}

	@Test
	public void test3() {
		String newStr = strHandler("  Hello World  ", (str) -> str.trim());
		System.out.println(newStr);

		String subStr = strHandler("Hello World", (str) -> str.substring(0, 5));
		System.out.println(subStr);
	}

	private String strHandler(String str, Function<String, String> fun) {
		return fun.apply(str);
	}

	@Test
	public void test4() {
		List<String> asList = Arrays.asList("hello", "world", "lambda", "ok", "zhou", "adc");
		List<String> filterList = filterStr(asList, (s) -> s.length() == 3);
		int size = filterList.size();
		for (int i = 0; i < size; i++) {
			System.out.println(filterList.get(i));
		}
	}

	private List<String> filterStr(List<String> list, Predicate<String> pre) {
		List<String> strList = new ArrayList<>();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			String str = list.get(i);
			if (pre.test(str)) {
				strList.add(str);
			}
		}
		return strList;
	}

}
