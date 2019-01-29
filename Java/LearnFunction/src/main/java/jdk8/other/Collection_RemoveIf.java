package jdk8.other;

import java.util.ArrayList;
import java.util.List;

public class Collection_RemoveIf {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("beijing");
        list.add("shanghai");
        list.add("shanghai");
        list.add("hangzhou");
        list.add("welcome");
        list.add("hi");
        list.removeIf("hello"::equals);
        list.replaceAll(
                item -> {
                    boolean equals = "shanghai".equals(item);
                    if (equals) return "111";
                    else return item;
                }
        );
        list.sort(String::compareTo);
        list.forEach(System.out::println);
    }

}
