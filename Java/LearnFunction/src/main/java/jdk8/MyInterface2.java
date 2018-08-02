package jdk8;

public interface MyInterface2 {

    default void myMethod() {
        System.out.println("default method2");
    }

}
