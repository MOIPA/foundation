package com.Java8.LambdaTest.practice;

@FunctionalInterface
//双参数
public interface MyFunctionTwo<T,R> {
    R calc(T t1, T t2);
}
