package jdk8;

public interface MyInterface1 {

    default void myMethod() {
        System.out.println("default method1");
    }

}
