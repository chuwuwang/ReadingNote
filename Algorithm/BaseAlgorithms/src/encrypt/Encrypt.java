package encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;

public class Encrypt {

    public static void main(String[] args) {
        byte[] key = hex2Bytes("1C5BC5650A2B293C91EFD19A7C025AA3");
        byte[] data = hex2Bytes("2ede86d10490df38a934b2fa9e6cad8e");
        try {
            byte[] bytes = Des3DecodeECB2(key, data);
            String res = bytes2Hex(bytes);
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encryptDataByPK(String pk, String data) {
        try {
            byte[] byteKey = hex2Bytes(pk);
            byte[] byteData = hex2Bytes(data);
            byte[] byteDecode = Des3DecodeECB2(byteKey, byteData);
            return bytes2Hex(byteDecode).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 十六进制转Byte
     */
    private static byte[] hex2Bytes(String hexStr) {
        int len = hexStr.length();
        if (len % 2 != 0) {
            throw new RuntimeException("length error");
        }
        byte[] b = new byte[hexStr.length() / 2];
        int bLen = b.length;
        int j = 0;
        for (int i = 0; i < bLen; i++) {
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

    private static String bytes2Hex(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 3des 双倍长
     * ECB解密,不要IV
     *
     * @param key  密钥
     * @param data Base64编码的密文
     * @return 明文
     * @throws Exception 异常
     */
    private static byte[] Des3DecodeECB2(byte[] key, byte[] data) throws Exception {
        byte[] tmpKey = new byte[24];
        System.arraycopy(key, 0, tmpKey, 0, 16);
        System.arraycopy(key, 0, tmpKey, 16, 8);
        String res = bytes2Hex(tmpKey);
        System.out.println(res);
        return Des3DecodeECB(tmpKey, data);
    }

    /**
     * ECB解密,不要IV
     *
     * @param key  密钥
     * @param data Base64编码的密文
     * @return 明文
     * @throws Exception 异常
     */
    private static byte[] Des3DecodeECB(byte[] key, byte[] data) throws Exception {
        Key desKey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        desKey = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, desKey);
        byte[] bytes = cipher.doFinal(data);
        return bytes;
    }


}
