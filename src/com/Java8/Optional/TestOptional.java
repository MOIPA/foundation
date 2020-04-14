package com.Java8.Optional;

import com.Java8.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * @author tr
 * @date 2020/4/14 17:14
 *
 * optional  用于避免空指针
 *
 * optional 容器类的常用方法
 * Optional.of(T t) 创建一个optional实例
 * Optional.empty() 创建一个空的实例
 * Optional.ofNullable(T t) 如果t不为空null，创建option实例，否则创建空实例
 * isPresent 判断是否包含值
 * orElse(T t) 如果对象包含实例，返回该值，否则返回t
 * orElseGet(supplier s) 如果对象包含值，返回，否则返回s获取的值
 * map(Function f) 如果有值对其处理，并返回处理后的 Optional 否则返回Optional.empty()
 * flatMap(Function mapper) 类似mapper 要求返回值为Optional
 *
 */
public class TestOptional {
    @Test
    public void test1() {
        // 如果of里面传入的null 立刻发送异常，用于快速定位问题
        Optional<Employee> op = Optional.of(new Employee());

        Employee employee1 = op.get();
        System.out.println(employee1);

        // 如果一定要optional内为null
        Optional<Employee> empty = Optional.empty();
        // null get不到值
        System.out.println(empty.get());

        // 构建空optional的另一个方法
        Optional<Employee> empty2 = Optional.ofNullable(null);
        System.out.println(empty2.get());

        //判断是否存在值
        if(empty2.isPresent()) System.out.println(empty.get());

        // 没值代替一个
        Employee employee2 = op.orElse(new Employee());

        // flatMap 要求返回用optiona容器包装
        Optional<String> s = op.flatMap(x -> Optional.of(x.getName()));
        System.out.println(s);
    }
}
