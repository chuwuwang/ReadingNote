package encrypt;

import string.ByteUtil;

import java.security.MessageDigest;

public class ShaUtil {

    public static void main(String[] args) throws Exception {
        String dataStr = "appleM00003A00000001100MYR123456789123456789";
        // byte[] data = ByteUtil.hexStr2Bytes(dataStr);
        byte[] data = dataStr.getBytes();
        byte[] sha_1 = sha_256(data);
        String hexStr = ByteUtil.bytes2HexStr(sha_1);
        System.out.println(hexStr);
    }

    public static byte[] sha_1(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.update(data);
        return digest.digest();
    }

    public static byte[] sha_256(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(data);
        return digest.digest();
    }

}
