package com.Java8.RefTest;

import com.Java8.Employee;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * 1.方法引用：三种引用格式
 * 对象::实例
 * <p>
 * 类::静态方法
 * <p>
 * 类::实例方法
 * 2. 构造器引用
 * ClassName::new
 * <p>
 * 3. 数组引用
 * Type::new
 */
public class refTest {


    // 对象::实例
    @Test
    public void test1() {
        Consumer<String> consumer = x -> System.out.println(x);
        //System.out.println实际被实现了，可以引用
        //注意：引用的时候，被引用方法/实例 的参数和返回值要和接口一致
        //这里自动将入参给System.out.print(),返回值和Consumer一样为void
        consumer = System.out::println;

    }

    @Test
    public void test2() {
        Employee employee = new Employee();
        int test = 1;
        Supplier<String> supplier = () -> {
            employee.setName("tst");
            return employee.getName();
        };
        System.out.println(supplier.get());
        //REF
        employee.setAge(8);
        Supplier<Integer> supplierRef = employee::getAge;
        System.out.println(supplierRef.get());
    }

    //类::静态方法名
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        //REF
        com = Integer::compare;
    }

    //类::实例方法
    @Test
    public void test4() {
        //注意：这里的方法体也被实现了，String的equals方法，但这是一个实例对象方法
        BiPredicate<String, String> predicate = (x, y) -> x.equals(y);
        //REF
        // java规定，如果第一个参数是调用函数者，第二个是被调者，可以用类名::方法名
        predicate = String::equals;

    }

    // 构造器引用
    @Test
    public void test5() {
        Supplier<Employee> supplier = () -> new Employee();
        //REF  无参数构造
        supplier = Employee::new;

        //REF 有参数构造
        //这里由于BiFunction接受2参数，返回一个，而Employee三个参数，所以手动写一个参数
        BiFunction<String, Integer, Employee> bf = (x, y) -> new Employee(x, y, 10);
        //REF 调用几个参数的构造器取决于函数接口的参数个数
        bf = Employee::new;//调用2参数构造器
        Employee tr = bf.apply("tr", 23);
        System.out.println(tr.toString());

    }

    //数组引用
    @Test
    public void test6() {
        Function<Integer, String[]> fun = x -> new String[x];
        String[] apply = fun.apply(3);
        //REF  和构造器引用一致
        fun = String[]::new;
        fun.apply(10);
    }
}

