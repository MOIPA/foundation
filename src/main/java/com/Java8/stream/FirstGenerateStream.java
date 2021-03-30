package com.Java8.stream;

import com.Java8.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FirstGenerateStream {

    // 创建stream
    @Test
    public void test1() {
//        1. 可以通过collection系列集合提供的steam（）或者 parallelStream（）获取流，前者串行流，后者并行流
        List<String> list = new ArrayList<String>();
        Stream<String> stream = list.stream();
//        2. 通过Arrays中的静态方法stream（）获取数组流
        Employee[] array = new Employee[3];
        Stream<Employee> stream1 = Arrays.stream(array);
//        3. 通过stream中的of方法创建流
        Stream<String> stream2 = Stream.of("a", "bb", "ccc");
//        4. 创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
//        stream3.forEach(System.out::println);
        // stream只有在被终端操作的时候才会执行
        stream3.limit(3).forEach(System.out::println);
        // 生成
        Stream<Double> stream4 = Stream.generate(() -> Math.random());
        stream4.limit(10).forEach(System.out::println);
    }


}
