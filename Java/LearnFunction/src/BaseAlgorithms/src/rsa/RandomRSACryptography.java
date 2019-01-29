package rsa;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RandomRSACryptography {

    public static String data = "hello world";

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = genKeyPair(1024);

        // 获取公钥，并以base64格式打印出来
        PublicKey publicKey = keyPair.getPublic();
        byte[] pubEncoded = publicKey.getEncoded();
        byte[] pubEncode = Base64.getEncoder().encode(pubEncoded);
        String str = new String(pubEncode);
        System.out.println("公钥：" + str);

        // 获取私钥，并以base64格式打印出来
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] priEncoded = privateKey.getEncoded();
        byte[] priEncode = Base64.getEncoder().encode(priEncoded);
        str = new String(priEncode);
        System.out.println("私钥：" + str);

        // 公钥加密
        byte[] encryptedBytes = encrypt(data.getBytes(), publicKey);
        System.out.println("加密后：" + new String(encryptedBytes));

        // 私钥解密
        byte[] decryptedBytes = decrypt(encryptedBytes, privateKey);
        System.out.println("解密后：" + new String(decryptedBytes));
    }

    // 生成密钥对
    private static KeyPair genKeyPair(int keyLength) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }

    // 公钥加密
    private static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    // 私钥解密
    private static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }


}
