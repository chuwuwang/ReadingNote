package jdk8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest1 {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "world", "hello world");
        TreeSet<String> treeSet = stream.collect(Collectors.toCollection(TreeSet::new));
        treeSet.forEach(System.out::println);
        System.out.println("-----------------------");

        List<String> stringList = Arrays.asList("hello", "world", "hello world", "test1");
        stringList.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("-----------------------");

        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        integerList.stream().map(item -> item * item).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("-----------------------");

        List<List<Integer>> asList = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4, 5),
                Arrays.asList(6, 7, 8)
        );
        asList.stream()
                .flatMap(Collection::stream)
                .map(item -> item * item)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("-----------------------");

        Stream.iterate(1, item -> item + 2).limit(6).forEach(System.out::println);
        System.out.println("-----------------------");

        Optional<Integer> reduce = Stream.iterate(1, item -> item + 2)
                .limit(6)
                .filter(item -> item > 2)
                .map(item -> item * 2)
                .skip(2)
                .limit(2)
                .reduce(Integer::sum);
        reduce.ifPresent(System.out::println);

    }

}
