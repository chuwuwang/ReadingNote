package jdk8;

import java.util.ArrayList;
import java.util.List;

public class ForEachTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.forEach(
                (integer) -> {
                    System.out.println(integer);
                    if (integer == 2) {
                        // break; // 不能用break
                    }
                }
        );
    }

}
