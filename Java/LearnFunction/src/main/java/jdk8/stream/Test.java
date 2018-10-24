package jdk8.stream;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509CertificateStructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) throws Exception {
        parse();
        testReadX509CerFile();
    }

    public static void parse() {
        try {
            InputStream inStream = new FileInputStream("d:/server_cert.cer");
            ASN1Sequence seq;
            ASN1InputStream aIn;
            aIn = new ASN1InputStream(inStream);
            seq = (ASN1Sequence) aIn.readObject();
            X509CertificateStructure cert = new X509CertificateStructure(seq);
            SubjectPublicKeyInfo subjectPublicKeyInfo = cert.getSubjectPublicKeyInfo();
            DERBitString publicKeyData = subjectPublicKeyInfo.getPublicKeyData();
            byte[] publicKey = publicKeyData.getEncoded();
            byte[] encodedPublicKey = publicKey;
            byte[] eP = new byte[64];
            System.arraycopy(encodedPublicKey, 4, eP, 0, eP.length);
            String hexStr = MyComparatorTest.bytes2HexStr(eP);
            System.out.println(hexStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 读取*.cer公钥证书文件， 获取公钥证书信息
     */
    public static void testReadX509CerFile() {
        try {
            // 读取证书文件
            File file = new File("d:/server_cert.cer");
            InputStream inStream = new FileInputStream(file);
            // 创建X509工厂类
            // CertificateFactory cf = CertificateFactory.getInstance("X.509");
            CertificateFactory cf = CertificateFactory.getInstance("X509");
            // 创建证书对象
            X509Certificate oCert = (X509Certificate) cf.generateCertificate(inStream);
            inStream.close();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String info = null;
            // 获得证书版本
            info = String.valueOf(oCert.getVersion());
            System.out.println("证书版本:" + info);
            // 获得证书序列号
            info = oCert.getSerialNumber().toString(16);
            System.out.println("证书序列号:" + info);
            // 获得证书有效期
            Date beforeDate = oCert.getNotBefore();
            info = dateFormat.format(beforeDate);
            System.out.println("证书生效日期:" + info);
            Date afterDate = oCert.getNotAfter();
            info = dateFormat.format(afterDate);
            System.out.println("证书失效日期:" + info);
            // 获得证书主体信息
            info = oCert.getSubjectDN().getName();
            System.out.println("证书拥有者:" + info);
            // 获得证书颁发者信息
            info = oCert.getIssuerDN().getName();
            System.out.println("证书颁发者:" + info);
            // 获得证书签名算法名称
            info = oCert.getSigAlgName();
            System.out.println("证书签名算法:" + info);
        } catch (Exception e) {
            System.out.println("解析证书出错！");
            e.printStackTrace();
        }
    }


}
