package encrypt;

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

    private static String toHex(int var) {
        StringBuilder sb = new StringBuilder();
        if (var / 16 == 0) {
            return toHexEn(var);
        } else {
            String pre = toHex(var / 16);
            int temp = var % 16;
            String result = toHexEn(temp);
            sb.append(pre).append(result);
        }
        return sb.toString();
    }

    private static String toHexEn(int var) {
        String result = "";
        switch (var) {
            case 10:
                result += "A";
                break;
            case 11:
                result += "B";
                break;
            case 12:
                result += "C";
                break;
            case 13:
                result += "D";
                break;
            case 14:
                result += "E";
                break;
            case 15:
                result += "F";
                break;
            default:
                result += var;
        }
        return result;
    }


}
