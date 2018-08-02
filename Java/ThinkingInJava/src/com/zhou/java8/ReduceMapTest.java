package com.zhou.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Test;

@SuppressWarnings("unused")
public class ReduceMapTest {
	
	List<Employee> emps = Arrays.asList(
			new Employee(102, "李四", 59, 7777.77),
			new Employee(101, "张三", 18, 9999.99),
			new Employee(103, "王五", 28, 3333.33),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(105, "田七", 38, 5555.55)
	);
	
	@Test
	public void test1() {
		Map<Integer, String> map = emps.stream()
			.collect(Collectors.toMap(Employee::getId, Employee::getName));
		for(Integer i : map.keySet()) {
			System.out.println(map.get(i));
		}
	}
	
	@Test
	public void test2() {
		Map<Integer, Employee> map = emps.stream()
			.collect(Collectors.toMap(Employee::getId, Function.identity()));
		for(Integer i : map.keySet()) {
			System.out.println(map.get(i));
		}
	}
	
	@Test
	public void test3() {
		Map<Double, List<Employee>> map = emps.stream()
				.collect(Collectors.groupingBy(Employee::getSalary));
			for(Double l : map.keySet()) {
				System.out.println(map.get(l));
			}
	}
	
	@Test
	public void test4() {
		IntStream intStream = IntStream.of(1,2,3,4,5,6);
		DoubleStream doubleStream = DoubleStream.of(1f,2f,3f,4f);
		LongStream longStream = LongStream.of(1l,2l,3l,4l,5l,6l);
		Map<Double, Long> map = emps.stream()
				.collect(Collectors.groupingBy(Employee::getSalary, Collectors.counting()));
			for(Double l : map.keySet()) {
				System.out.println(map.get(l));
			}
	}
	
	@Test
	public void test5() {
		List<String> list = new ArrayList<>();
		Stream<String> stream = list.stream();
		list.add("hello");
		long count = stream.distinct().count();

		List<String> list2 = new ArrayList<>();
		Stream<String> stream2 = list2.stream();
		stream2.forEach(s -> {
			if (s.length() < 12) {
				list2.remove(s);
			}
		});
	}
	
}
