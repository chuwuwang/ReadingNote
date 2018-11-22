package algorithm;

import java.security.MessageDigest;

public class ShaUtil {

    public static void main(String[] args) throws Exception {
        String dataStr = "02003020078020C1020400000000000001602100203000710001026200375413330089601067D2512202012340917299903132393534373133303030303030303130333039343636000620202020202001405F2A0204585F340101820259808407A0000000041010950500000080019A031810259C01009F02060000000160219F03060000000000009F090200029F10120110A04009240000000000000000000000FF9F1A0204589F1E085251533031535A319F260888944E3B504955A29F2701809F33032008089F34031F03029F3501259F360200019F3704A56EF7240006303030393437";
        byte[] data = ByteUtil.hexStr2Bytes(dataStr);
        byte[] sha_1 = sha_1(data);
        String hexStr = ByteUtil.bytes2HexStr(sha_1);
        System.out.println(hexStr);
    }

    public static byte[] sha_1(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.update(data);
        return digest.digest();
    }

}
