package jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StreamTest {

    public static void main(String[] args) {
        IntStream.range(3, 8).forEach(System.out::println);
        System.out.println("----------------------------");

        IntStream.rangeClosed(3, 8).forEach(System.out::println);
        System.out.println("----------------------------");

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer sum = list.stream().map(i -> 2 * i).reduce(0, Integer::sum);
        System.out.println(sum);

    }

}
