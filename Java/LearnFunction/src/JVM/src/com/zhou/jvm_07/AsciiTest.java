package com.zhou.jvm_07;

import java.io.UnsupportedEncodingException;

public class AsciiTest {

	public static void main(String[] args) {
		String value = "P10S";
		try {
			byte[] bytes = value.getBytes("US-ASCII");
			for (byte v : bytes) {
				System.out.println(v);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("-----------------");
		String hexStrSN = asciiStr2HexStr(value);
		byte[] hexStr2Bytes = hexStr2Bytes(hexStrSN);
		for (byte v : hexStr2Bytes) {
			System.err.println(v);
		}
	}

	public static String asciiStr2HexStr(String ascii) {
		byte[] dat = asciiStr2Bytes(ascii);
		return byte2HexStr(dat);
	}

	public static byte[] asciiStr2Bytes(String ascii) {
		byte[] dat = null;
		try {
			dat = ascii.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return dat;
	}

	public static String byte2HexStr(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0xFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	/**
	 * 十六进制字符串转换成bytes
	 */
	public static byte[] hexStr2Bytes(String hexStr) {
		int l = hexStr.length();
		if (l % 2 != 0) {
			StringBuilder sb = new StringBuilder(hexStr);
			sb.insert(hexStr.length() - 1, '0');
			hexStr = sb.toString();
		}
		byte[] b = new byte[hexStr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexStr.charAt(j++);
			char c1 = hexStr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}

	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

}
