package com.Java8.LambdaTest.Inherit;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * java8 四大内置核心函数接口
 *
 * Consumer<T> 消费性接口
 *      void accept(T t)
 * Supplier<T> 供给型接口
 *      T get();
 * function<T,R> 函数型接口
 *      R apply(T t)
 * Predicate<T> 断言
 *      boolean test(T t);
 *
 */
public class Examples {

    @Test
    public void testConsumer() {
        LogConsumer("hello consumer", x-> System.out.println("log:"+x));
    }

    private void LogConsumer(String message, Consumer<String> consumer) {
        consumer.accept(message);
    }

    @Test
    public void testSupplier() {
        Supplier<Integer> supplier = ()->(int)(Math.random()*100);
        System.out.println(supplier.get());
    }

    @Test
    public void testFunction() {
        Function<String, Integer> function = (x) -> Integer.parseInt(x);
        System.out.println(function.apply("11"));
        }

}
