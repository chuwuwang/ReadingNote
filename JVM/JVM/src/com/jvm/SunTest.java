package com.jvm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SunTest {

	public static void main(String[] args) {
		short value = 0x0102;
		byte[] data = new byte[4];
		data[0] = (byte) (value >> 8);
		data[1] = (byte) (value);
		for (byte b : data) {
			System.out.println(b);
		}

		parseTrack2("622878220609728299F=000012011002394731");
	}

	private static void parseTrack2(String track2) {
		String filter = stringFilter(track2);
		int index = filter.indexOf("=");
		String cardNumber = filter.substring(0, index);
		System.out.println(cardNumber);
		String expiryDate = filter.substring(index + 1, index + 5);
		System.out.println(expiryDate);
		String serviceCode = filter.substring(index + 5, index + 8);
		System.out.println(serviceCode);
	}

	/**
	 * 只允许数字和=号
	 */
	private static String stringFilter(String str) {
		String regEx = "[^0-9=]";
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(str);
		return matcher.replaceAll("").trim();
	}

}
