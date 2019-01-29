package string;

public class ByteUtil {

    public static String bytes2HexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String temp;
        for (byte b : bytes) {
            // 将每个字节与0xFF进行与运算，然后转化为10进制，然后借助于Integer再转化为16进制
            temp = Integer.toHexString(0xFF & b);
            if (temp.length() == 1) {
                // 每个字节8为，转为16进制标志，2个16进制位
                temp = "0" + temp;
            }
            sb.append(temp);
        }
        return sb.toString().toUpperCase();
    }

    public static String byte2HexStr(byte b) {
        String temp = Integer.toHexString(0xFF & b);
        if (temp.length() == 1) {
            temp = "0" + temp;
        }
        return temp;
    }

    public static byte[] hexStr2Bytes(String hexStr) {
        hexStr = hexStr.toLowerCase();
        int length = hexStr.length();
        byte[] bytes = new byte[length >> 1];
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (index > hexStr.length() - 1) return bytes;
            byte highDit = (byte) (Character.digit(hexStr.charAt(index), 16) & 0xFF);
            byte lowDit = (byte) (Character.digit(hexStr.charAt(index + 1), 16) & 0xFF);
            bytes[i] = (byte) (highDit << 4 | lowDit);
            index += 2;
        }
        return bytes;
    }

    public static byte hexStr2Byte(String hexStr) {
        return (byte) Integer.parseInt(hexStr, 16);
    }


}
