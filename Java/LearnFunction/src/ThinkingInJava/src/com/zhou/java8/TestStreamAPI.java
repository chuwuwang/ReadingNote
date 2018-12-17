package com.zhou.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class TestStreamAPI {

	/**
	 * 1. ����һ�������б���η���һ����ÿ������ƽ�����ɵ��б��أ� ��������1��2��3��4��5���� Ӧ�÷��ء�1��4��9��16��25����
	 */
	@Test
	public void test1() {
		Integer[] nums = new Integer[] { 1, 2, 3, 4, 5 };
		Arrays.stream(nums).map((x) -> x * x).forEach(System.out::println);
	}
	
	/*
	 * 2.������ map �� reduce ������һ�������ж��ٸ�Employee�أ�
	 */
	List<Employee> emps = Arrays.asList(
			new Employee(102, "����", 59, 6666.66),
			new Employee(101, "����", 18, 9999.99),
			new Employee(103, "����", 28, 3333.33),
			new Employee(104, "����", 8, 7777.77),
			new Employee(104, "����", 8, 7777.77),
			new Employee(104, "����", 8, 7777.77),
			new Employee(105, "����", 38, 5555.55)
	);
	
	@Test
	public void test2() {
		Optional<Integer> optional = emps.stream()
			.map((e) -> 1)
			.reduce(Integer::sum);
		System.out.println(optional.get());
	}
	
	
}
