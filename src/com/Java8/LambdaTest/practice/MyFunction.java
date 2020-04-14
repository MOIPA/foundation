package com.Java8.LambdaTest.practice;

@FunctionalInterface
public interface MyFunction<T> {
    Integer calc(T t);
}
