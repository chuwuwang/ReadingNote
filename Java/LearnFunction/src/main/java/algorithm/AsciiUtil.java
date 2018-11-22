package algorithm;

public class AsciiUtil {

    public static void main(String[] args) {

    }

    public static String asciiToString(String value) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < value.length() / 2; i++) {
            String var = value.substring(i * 2, (i + 1) * 2);
            char c = (char) Integer.parseInt(var, 16);
            sb.append(c);
        }
        return sb.toString();
    }

    public static String stringToAscii(String str) {
        StringBuilder sb = new StringBuilder();
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            byte var = bytes[i];
            String toHex = toHex(var);
            sb.append(toHex);
        }
        return sb.toString();
    }

    public static String toHex(int n) {
        StringBuilder sb = new StringBuilder();
        if (n / 16 == 0) {
            return toHexEn(n);
        } else {
            String t = toHex(n / 16);
            int nn = n % 16;
            String toHexEn = toHexEn(nn);
            sb.append(t).append(toHexEn);
        }
        return sb.toString();
    }

    private static String toHexEn(int n) {
        String rt = "";
        switch (n) {
            case 10:
                rt += "A";
                break;
            case 11:
                rt += "B";
                break;
            case 12:
                rt += "C";
                break;
            case 13:
                rt += "D";
                break;
            case 14:
                rt += "E";
                break;
            case 15:
                rt += "F";
                break;
            default:
                rt += n;
        }
        return rt;
    }


}
