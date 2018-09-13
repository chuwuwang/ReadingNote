package jdk8.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyComparatorTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("ni hao", "hello", "world", "welcome");
        Collections.sort(list,
                (i1, i2) -> i1.length() - i2.length()
        );
        System.out.println(list);

    }

}
