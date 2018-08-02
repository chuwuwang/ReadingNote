package com.zhou.java8;

import java.util.Optional;

import org.junit.Test;

public class TestOptional {
	
	@Test
	public void test1() {
		Optional<Employee> optional = Optional.of(new Employee());
		Employee employee = optional.get();
		System.out.println(employee);
	}
	
	@Test
	public void test2() {
		Optional<Employee> optional = Optional.empty();
		if (optional.isPresent()) {
			System.out.println(optional.get());
		} else {
			System.out.println("optional is null");
		}
	}
	
	@Test
	public void test3() {
		Optional<Employee> optional = Optional.empty();
		Employee employee = optional.orElse(new Employee());
		System.out.println(employee);
	}
	
}
