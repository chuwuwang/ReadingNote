package com.zhou.java8;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

import org.junit.Test;

public class TestLocalDateTime {
	
	/**
	 * 1. LocalDate、LocalTime、LocalDateTime
	 */
	@Test
	public void test1() {
		LocalDateTime time = LocalDateTime.now();
		System.out.println(time);
		
		LocalDateTime time2 = LocalDateTime.of(2017, 4, 24, 22, 43, 50);
		System.out.println(time2);
		
		LocalDateTime time3 = time2.plusYears(2);
		System.out.println(time3);
		
		LocalDateTime time4 = time2.minusMonths(2);
		System.out.println(time4);
		
		System.out.println(time.getYear());
		System.out.println(time.getMonthValue());
		System.out.println(time.getDayOfMonth());
		System.out.println(time.getHour());
		System.err.println(time.getMinute());
		System.out.println(time.getSecond());
	}
	
	/**
	 * 2. Instant : 时间戳。 （使用 Unix 元年  1970年1月1日 00:00:00 所经历的毫秒值）
	 */
	@Test
	public void test2() {
		Instant instant = Instant.now(); // 默认使用 UTC 时区
		System.out.println(instant);
		
		OffsetDateTime time = instant.atOffset(ZoneOffset.ofHours(8));
		System.out.println(time);
		
		System.out.println(instant.getNano());
		
		Instant instant2 = Instant.ofEpochSecond(5);
		System.out.println(instant2);
	}
	
	/**
	 * 3.Duration : 用于计算两个“时间”间隔   Period : 用于计算两个“日期”间隔
	 */
	@Test
	public void test3() {
		Instant instant = Instant.now();
		
		System.out.println("--------------------");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Instant instant2 = Instant.now();
		System.out.println("所耗费时间为：" + Duration.between(instant, instant2));
		System.out.println("--------------------");
		
		LocalDate date = LocalDate.now();
		LocalDate date2 = LocalDate.of(2016, 3, 15);
		
		Period period = Period.between(date2, date);
		System.out.println(period.getYears());
		System.out.println(period.getMonths());
		System.out.println(period.getDays());
	}
	
	/**
	 * 4. TemporalAdjuster : 时间校正器
	 */
	@Test
	public void test4() {
		LocalDateTime time = LocalDateTime.now();
		System.out.println(time);
	
		LocalDateTime time2 = time.withDayOfMonth(10);
		System.out.println(time2);
		
		LocalDateTime time3 = time.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println(time3);
		
		
		LocalDateTime time5 = time.with((l) -> {
			LocalDateTime time4 = (LocalDateTime) l;
			DayOfWeek week = time4.getDayOfWeek();
			if (week.equals(DayOfWeek.FRIDAY)) {
				return time4.plusDays(3);
			} else if (week.equals(DayOfWeek.SATURDAY)) {
				return time4.plusDays(2);
			} else {
				return time4.plusDays(1);
			}
		});
		System.out.println(time5);
	}
	
	/**
	 * 5. DateTimeFormatter : 解析和格式化日期或时间
	 */
	@Test
	public void test5() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");
		LocalDateTime time = LocalDateTime.now();
		String format = time.format(formatter);
		System.out.println(format);
	
		LocalDateTime time2 = LocalDateTime.parse(format, formatter);
		System.out.println(time2);
	}
	
	/**
	 * 6.ZonedDate、ZonedTime、ZonedDateTime ： 带时区的时间或日期
	 */
	@Test
	public void test6() {
		LocalDateTime time = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
		System.out.println(time);
		
		ZonedDateTime time2 = ZonedDateTime.now(ZoneId.of("US/Pacific"));
		System.out.println(time2);
	}
	
	@Test
	public void test7() {
		Set<String> set = ZoneId.getAvailableZoneIds();
		set.forEach(System.out::println);
	}
	
}
