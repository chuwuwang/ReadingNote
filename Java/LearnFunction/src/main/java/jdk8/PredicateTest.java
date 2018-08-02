package jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        PredicateTest predicateTest = new PredicateTest();

        predicateTest.conditionFilter(list, item -> item % 2 == 0);
        System.out.println("------------------------------------");

        predicateTest.conditionFilter(list, item -> item > 5, item -> item % 2 == 0);
    }

    private void conditionFilter(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer integer : list) {
            boolean test = predicate.test(integer);
            if (test) {
                System.out.println(integer);
            }
        }
    }

    private void conditionFilter(List<Integer> list, Predicate<Integer> predicate, Predicate<Integer> other) {
        for (Integer integer : list) {
            boolean test = predicate.and(other).test(integer);
            if (test) {
                System.out.println(integer);
            }
        }
    }

    private Predicate<String> isEqual(Object object) {
        return Predicate.isEqual(object);
    }


}
