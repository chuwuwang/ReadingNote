package com.zhou.java8;

@FunctionalInterface
public interface MyFunction2<T, R> {

	R getValue(T t1, T t2);

}
