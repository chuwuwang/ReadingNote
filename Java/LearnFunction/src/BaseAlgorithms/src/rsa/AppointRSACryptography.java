package rsa;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class AppointRSACryptography {

    public static void main(String[] args) throws Exception {
        String d = "B2D6BEF9E7DF7D5266867610110778ADA9E692F27E571B58425BBBA9ECB8B05A82C3AF6BA25E4F2AD647ACBBE007425BA3452E60017B1D83AC36D42B227C638819831627B4C7CD754F888270FD5DD4FEE0DDED81BA0341EDF73F6B4DDA6AD727C06457E3A07DB77493D677E6FD20671E2F7D513B775C47F61E83CE51338F23A9";
        String n = "C3FC769CEF5522C596C2036D4524FF3D15875D44C67A6D630881798CB4669ADD516ED99ED3A37DDA95894EF91D96C3AF27173D0F9EA71280185C0520CE5428BA27131962B5876E3265F1DCC5BF746BE61A2BD4C0F031A699E812C72E93C8D9EF5DB8275756A8AF640BEA2C9696CC8BB41607E85A2D0C1751355A13970A682623";
        String e = "65537";
        String data = "hello world";

        // 公钥加密
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        BigInteger modulus = new BigInteger(n, 16);
        BigInteger publicExponent = new BigInteger(e);
        RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, publicExponent);
        PublicKey publicKey = keyFactory.generatePublic(rsaPublicKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = data.getBytes();
        byte[] encryptData = cipher.doFinal(bytes);

        // 私钥解密
        BigInteger privateExponent = new BigInteger(d, 16);
        RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulus, privateExponent);
        PrivateKey privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptData = cipher.doFinal(encryptData);
        String str = new String(decryptData);
        System.out.println("decryptData:" + str);
    }


}
