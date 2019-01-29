package jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class MethodReferenceTest {

    public static void main(String[] args) {
        List<String> cities = Arrays.asList("chongqing", "shanghai", "beijing", "chengdu");
        Collections.sort(cities,
                (c1, c2) -> c1.compareToIgnoreCase(c2)
        );

        Collections.sort(cities,
                String::compareToIgnoreCase
        );

        cities.sort(String::compareToIgnoreCase);


        MethodReferenceTest test = new MethodReferenceTest();
        String string = test.getString(String::new);
        System.out.println(string);
    }

    public String getString(Supplier<String> supplier) {
        return supplier.get() + "test";
    }

}
