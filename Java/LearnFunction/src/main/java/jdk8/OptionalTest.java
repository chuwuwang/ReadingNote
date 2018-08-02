package jdk8;

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        Optional<String> optional = Optional.of("hello");

        // style 1
        boolean present = optional.isPresent();
        if (present) {
            String val = optional.get();
            System.out.println(val);
        }
        System.out.println("--------------------------");

        // style 2
        optional.ifPresent(
                System.out::println
        );
        System.out.println("--------------------------");

        String world = optional.orElse("world");
        System.out.println(world);
        System.out.println("--------------------------");

        String orElseGet = optional.orElseGet(
                () -> "keep"
        );
        System.out.println(orElseGet);
        System.out.println("--------------------------");


    }

}
