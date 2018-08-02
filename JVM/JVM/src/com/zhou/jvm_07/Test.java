package com.zhou.jvm_07;

public class Test {
	static {
		i = 0; // 给变量赋值可以正常编译通过
		// System.out.print(i); // 这句编译器会提示"非法向前引用"
	}
	static int i = 1;

	static class Parent {
		public static int A = 1;
		static {
			A = 2;
		}
	}

	static class Sub extends Parent {
		public static int B = A;
	}
	
	public static void main(String[] args) {
		// System.out.println(Sub.B);
		byte[] bs = new byte[] {11, 22, 33, 44, 55, 66};
		System.out.println(bytesToHexString(bs));
		System.out.println(hexEncode(bs, 0, bs.length));
	}
	
    public static String bytesToHexString(byte[] src) {
        if (src == null || src.length <= 0) {
            return null;
        }
        byte[] nsrc = new byte[src.length];
        System.arraycopy(src, 0, nsrc, 0, src.length);
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < nsrc.length; i++) {
            int v = nsrc[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    
    static final char[] HEX = "0123456789ABCDEF".toCharArray();
    
    public static String hexEncode(byte[] buffer, int start, int length) {
        if (buffer.length == 0) {
            return "";
        }
        int holder = 0;
        char[] chars = new char[length * 2];
        int pos = -1;
        for (int i = start; i < start + length; i++) {
            holder = (buffer[i] & 0xF0) >> 4;
            chars[++pos * 2] = HEX[holder];
            holder = buffer[i] & 0x0F;
            chars[(pos * 2) + 1] = HEX[holder];
        }
        return new String(chars);
    }
	
}
