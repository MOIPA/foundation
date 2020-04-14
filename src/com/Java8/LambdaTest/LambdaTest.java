package com.Java8.LambdaTest;

import com.Java8.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class LambdaTest {

    //原来的匿名内部类
    @Test
    public void test1(){
        Comparator<Integer> integerComparator = new Comparator<Integer>(){
            @Override
            public int compare(Integer integer, Integer t1) {
                return Integer.compare(integer, t1);
            }
        };
    }

    // lambda
    @Test
    public void test2() {
        Comparator<Integer> integerComparator = (x, y) -> Integer.compare(x, y);
    }

    //获取公司年龄大于35的
    List<Employee> employees = Arrays.asList(
            new Employee("test1", 18, 999.9),
            new Employee("test2", 28, 999.9),
            new Employee("test3", 38, 999.9),
            new Employee("test4", 48, 999.9)
    );

    @Test
    public void test3() {
        List<Employee> employees = filterEmployees(this.employees);
        for (Employee employee:employees) {
            System.out.println(employee.toString());
        }
    }

    public List<Employee> filterEmployees(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee e :
                list) {
            if (e.getAge() > 35) {
                emps.add(e);
            }
        }
        return emps;
    }
    // 需求：年龄大于35，且工资大于500的
    //...... 重复代码

    //优化1：策略模式
    public List<Employee> filterEmployee(List<Employee> list, MyPredict<Employee> predict) {
        List<Employee> emps = new ArrayList<>();
        for (Employee e :
                list) {
            if (predict.test(e)) {
                emps.add(e);
            }
        }
        return emps;
    }

    @Test
    public void test4() {
        employees = filterEmployee(this.employees, new FilterEmployeeByAge());
        for (Employee employee: this.employees) {
            System.out.println(employee.toString());
        }
    }

    //优化2:匿名内部类
    @Test
    public void test5() {
        employees = filterEmployee(this.employees, new MyPredict<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return false;
            }
        });
        for (Employee employee: this.employees) {
            System.out.println(employee.toString());
        }
    }

    //优化3：lambda
    @Test
    public void test6() {
        employees = filterEmployee(employees, (ep) -> ep.getSalary() > 300);
    }

    //优化4：lambda+steam
    @Test
    public void test7() {
        employees.stream().filter((e)->e.getAge()>18)
                .forEach(System.out::println);
    }

    // Lambda 需要函数式接口的支持
    @Test
    public void test8() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello java");
            }
        };
        r1.run();
        Runnable r2 = () -> System.out.println("hello lambda");
        r2.run();

    }

    // 一个参数无返回值
    @Test
    public void test9() {

        Consumer<String> test = (x) -> System.out.println(x);
        //如果参数只有一个 （）可以不写
        Consumer<String> test2 =x -> System.out.println(x);
        test.accept("lambda");
        test2.accept("new lambda");

    }
    // 两个以上的参数，且多条语句，有返回值
    @Test
    public void test10() {
        Comparator<Integer> com = (x,y)->{
            System.out.println("result");
            return Integer.compare(x, y);
        };
        com.compare(1, 2);
    }
    //如果lambda 只有一条，那么return和{}可以不写
    @Test
    public void test11() {
        Comparator<Integer> com = (x,y)->Integer.compare(x, y);
        com.compare(1, 2);
    }

    // lambda 表达式的参数列表数据类型可以不写，jvm自动通过上下文推断数据类型（类型推断），写数据类型也可以
    // 写接口的时候可以在开头添加@FunctionalInterface 标识一下这是函数式接口

}

