package com.Java8.stream;


import com.Java8.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 获取stream后的操作
 * 1. filter 筛选
 * 2. limit 截断
 * 3. skip 跳过
 * 4. distinct 去重
 */
public class SecondOperateStream {

    List<Employee> employees = Arrays.asList(
            new Employee("test1", 18, 999.9),
            new Employee("test2", 28, 999.9),
            new Employee("test3", 38, 999.9),
            new Employee("test4", 48, 999.9),
            new Employee("test4", 48, 999.9),
            new Employee("test4", 48, 999.9)
    );

    @Test
    public void test1() {
        // limit
        // 中间操作：不执行
        Stream<Employee> employeeStream = employees.stream()
                .filter((e) -> e.getAge() > 28)
                .limit(2);// 一旦数据量到达，不再执行后续操作：短路
        //终端操作：一次性执行全部内容，惰性求值
        employeeStream.forEach(System.out::println);
    }

    @Test
    public void test2() {
        //skip
        employees.stream()
                .filter((e) -> e.getAge() > 0)
                .skip(1)
                .distinct() // 注意 去重的对象要重写hashcode和equals，看employee类实现
                .forEach(System.out::println);
    }

    //Map操作：将函数作用于每个元素输出
    @Test
    public void test3() {
        List<String> list = Arrays.asList("111", "bbb", "ccc");
        list.stream().map((x) -> x.toUpperCase())
                .forEach(System.out::println);
        employees.stream().map(Employee::getName)
                .forEach(System.out::println);
    }

    //flatMap操作:将流中的每个元素转为流，再将流拼接
    @Test
    public void test4() {
        List<String> list = Arrays.asList("111", "bbb", "ccc");
//        Stream<char[]> stream = list.stream().flatMap((ele) -> List.of(ele.toCharArray()).stream());
        Stream<Character> stream = list.stream()
                .flatMap((ele) -> {
                    List<Character> temp = new ArrayList<>();
                    for (Character c : ele.toCharArray()) temp.add(c);
                    return temp.stream();
                });
        stream.forEach(System.out::println);

    }

    //排序
    //sorted()自然排序
    //sorted(Comparator com)定制排序
    @Test
    public void test5() {

        //自然排序
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        list.stream().sorted().forEach(System.out::println);

        //定制排序
        employees.stream().sorted((x1,x2)->{
            if (x1.getAge()==x2.getAge())
                return x1.getName().compareTo(x2.getName());
            return x2.getAge() - x1.getAge();
        }).forEach(System.out::println);
    }

}
