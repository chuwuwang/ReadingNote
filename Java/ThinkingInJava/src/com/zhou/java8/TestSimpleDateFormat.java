package com.zhou.java8;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class TestSimpleDateFormat {

	@Test
	public void test1() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Callable<Date> task = new Callable<Date>() {

			@Override
			public Date call() throws Exception {
				return sdf.parse("20170114");
			}

		};

		ExecutorService pool = Executors.newFixedThreadPool(10);
		List<Future<Date>> futures = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			futures.add(pool.submit(task));
		}

		for (Future<Date> future : futures) {
			System.out.println(future.get());
		}

		pool.shutdown();
	}

	/**
	 * 解决多线程安全问题
	 */
	@Test
	public void test2() throws Exception {
		Callable<Date> task = new Callable<Date>() {

			@Override
			public Date call() throws Exception {
				return DateFormatThreadLocal.convert("20170114");
			}

		};

		ExecutorService pool = Executors.newFixedThreadPool(10);
		List<Future<Date>> futures = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			futures.add(pool.submit(task));
		}

		for (Future<Date> future : futures) {
			System.out.println(future.get());
		}

		pool.shutdown();
	}

	@Test
	public void test3() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		Callable<LocalDate> task = new Callable<LocalDate>() {

			@Override
			public LocalDate call() throws Exception {
				LocalDate date = LocalDate.parse("20170114", formatter);
				return date;
			}

		};

		ExecutorService pool = Executors.newFixedThreadPool(10);
		List<Future<LocalDate>> futures = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			futures.add(pool.submit(task));
		}

		for (Future<LocalDate> future : futures) {
			System.out.println(future.get());
		}

		pool.shutdown();
	}

}

class DateFormatThreadLocal {

	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMdd");
		}

	};

	public static final Date convert(String source) throws ParseException {
		return df.get().parse(source);
	}

}
