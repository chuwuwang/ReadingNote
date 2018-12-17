package encode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GBKEncode {

    public static void main(String[] args) {
        String gb2312ToUtf8 = gb2312ToUtf8("消费撤销");
        System.out.print(gb2312ToUtf8);
    }

    public static String gb2312ToUtf8(String str) {
        String urlEncode = "";
        try {
            urlEncode = URLEncoder.encode(str, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlEncode;
    }

}
