package jdk8;

public class DefaultMethodTest implements MyInterface1, MyInterface2 {

    public static void main(String[] args) {
        DefaultMethodTest test = new DefaultMethodTest();
        test.myMethod();
    }

    @Override
    public void myMethod() {
        MyInterface2.super.myMethod();
    }

}