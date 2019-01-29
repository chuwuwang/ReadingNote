package jdk8.stream;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MyComparatorTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("ni hao", "hello", "world", "welcome");
        Collections.sort(list,
                (i1, i2) -> i1.length() - i2.length()
        );
        System.out.println(list);

        m1FormatBlock(1, 0);

    }

    public static byte[] m1FormatBlock(int blockIndex, int data) {
        byte[] result = new byte[16];
        int posIndex = 15;

        result[posIndex] = (byte) ~(blockIndex & 0xFF);
        posIndex--;
        result[posIndex] = (byte) (blockIndex & 0xFF);
        posIndex--;
        result[posIndex] = (byte) ~(blockIndex & 0xFF);
        posIndex--;
        result[posIndex] = (byte) (blockIndex & 0xFF);
        posIndex--;

        for (int i = 0; i < 4; i++) {
            int temp = data >> 8 * i;
            result[posIndex] = (byte) (temp & 0xFF);
            posIndex--;
        }

        for (int i = 0; i < 4; i++) {
            int temp = (~data) >> 8 * i;
            result[posIndex] = (byte) (temp & 0xFF);
            posIndex--;
        }

        for (int i = 0; i < 4; i++) {
            int temp = data >> 8 * i;
            result[posIndex] = (byte) (temp & 0xFF);
            posIndex--;
        }

        String hexStr = bytes2HexStr(result);
        System.out.println(hexStr);

        String str = hexStr2Str("00010203040506070809");
        System.out.println(str);

        String asciiStr = hexStr2AsciiStr("00010203040506070809");
        System.out.println(asciiStr);

        return result;
    }

    public static String bytes2HexStr(byte[] bytes) {
        BigInteger bigInteger = new BigInteger(1, bytes);
        return bigInteger.toString(16);
    }

    public static String hexStr2Str(String hexStr) {
        String vi = "0123456789ABC DEF".trim();
        char[] array = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int temp;
        for (int i = 0; i < bytes.length; i++) {
            char c = array[2 * i];
            temp = vi.indexOf(c) * 16;
            c = array[2 * i + 1];
            temp += vi.indexOf(c);
            bytes[i] = (byte) (temp & 0xFF);
        }
        return new String(bytes);
    }

    public static String hexStr2AsciiStr(String hexStr) {
        String vi = "0123456789ABC DEF".trim();
        hexStr = hexStr.trim().replace(" ", "").toUpperCase(Locale.US);
        char[] array = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int temp = 0x00;
        for (int i = 0; i < bytes.length; i++) {
            char c = array[2 * i];
            temp = vi.indexOf(c) << 4;
            c = array[2 * i + 1];
            temp |= vi.indexOf(c);
            bytes[i] = (byte) (temp & 0xFF);
        }
        return new String(bytes);
    }


}
