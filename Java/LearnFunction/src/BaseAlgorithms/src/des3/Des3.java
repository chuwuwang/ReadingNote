package des3;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;

public class Des3 {

    public static void main(String[] args) {
        byte[] data = hexStr2Bytes("0000000000000000");
        byte[] key = hexStr2Bytes("33343330323437323931373231373333");
        byte[] bytes = des3Encrypt(key, data);
        if (bytes != null) {
            String cv = byte2HexStr(bytes);
            System.out.println("随机密钥校验值:" + cv);
        }
    }

    private static byte[] des3Encrypt(byte[] key, byte[] data) {
        byte[] temp = new byte[24];
        System.arraycopy(key, 0, temp, 0, 16);
        System.arraycopy(key, 0, temp, 16, 8);
        try {
            DESedeKeySpec spec = new DESedeKeySpec(temp);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            Key desKey = secretKeyFactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String byte2HexStr(byte[] b) {
        String str = "";
        String temp = "";
        for (int n = 0; n < b.length; n++) {
            temp = Integer.toHexString(b[n] & 0xFF);
            if (temp.length() == 1)
                str = str + "0" + temp;
            else
                str = str + temp;
        }
        return str.toUpperCase();
    }

    private static byte[] hexStr2Bytes(String hexStr) {
        int l = hexStr.length();
        if (l % 2 != 0) {
            StringBuilder sb = new StringBuilder(hexStr);
            sb.insert(hexStr.length(), '0');
            hexStr = sb.toString();
        }
        byte[] b = new byte[hexStr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexStr.charAt(j++);
            char c1 = hexStr.charAt(j++);
            int temp = (parse(c0) << 4) | parse(c1);
            b[i] = (byte) temp;
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
