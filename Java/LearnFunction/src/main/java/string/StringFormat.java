package string;

public class StringFormat {

    public static void main(String[] args) {
        stringForRightAddSpace();
    }

    // 左补空格
    public static void stringForLeftAddSpace() {
        String str = String.format("% 4d", 12);
        System.out.println(str); //   12
    }

    // 左补0
    public static void stringForLeftAdd0() {
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        String str = String.format("%04d", 12);
        System.out.println(str); // 0012
    }

    // 右补0
    public static void stringForRightAdd0() {
        String str = String.format("%-5d", 12);
        str = str.replace(" ", "0");
        System.out.println(str); // 12000
    }

    // 右补空格
    public static void stringForRightAddSpace() {
        String str = String.format("%-5d", 12);
        System.out.println(str); // 12
    }

}
