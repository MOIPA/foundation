package com.Java8.stream;

import com.Java8.Employee;
import com.Java8.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tr
 * @date 2020/4/14 10:55
 * <p>
 * 对stream的终止操作
 * <p>
 * 查找与匹配
 * allMatch--检查是否匹配所有元素
 * anyMatch--检查是否少匹配一个元素
 * noneMatch--检查是否没有匹配所有元素
 * findFirst--返回第一个元素
 * findAny--返回当前流中的任意元素
 * count--返回流中的元素总数
 * max--返回流中的最大值
 * min--返回流中最小值
 * 规约，收集
 */
public class TerminateOperateStream {

    List<Employee> employees = Arrays.asList(
            new Employee("test1", 18, 919.9, Status.FREE),
            new Employee("test2", 17, 299.9, Status.BUSY),
            new Employee("test3", 28, 599.9, Status.VOCATION),
            new Employee("test4", 38, 1999.9, Status.FREE),
            new Employee("test5", 58, 599.9, Status.BUSY),
            new Employee("test6", 48, 499.9, Status.VOCATION)
    );

    /**
     * .......
     */
    @Test
    public void test1() {
        // 是否所有人都忙  检查匹配所有元素
        boolean ifAllBusy = employees.stream()
                .allMatch((employee -> employee.getStatus().equals(Status.BUSY)));
        System.out.println(ifAllBusy);
        // 是否有人忙
        boolean ifAnyBusy = employees.stream().anyMatch((employee -> employee.getStatus().equals(Status.BUSY)));
        System.out.println(ifAnyBusy);

        //是否没人忙
        boolean noneBusy = employees.stream().noneMatch(employee -> employee.getStatus().equals(Status.BUSY));
        System.out.println(noneBusy);

        //第一个  工资排序
        // Optional 容器类，内部不为空，若为空有替代
        Optional<Employee> first = employees.stream().sorted(((t1, t2) -> Double.compare(t1.getSalary(), t2.getSalary())))
                .findFirst();
        System.out.println(first.get());

        // 随便找个空闲的人
        Optional<Employee> any = employees.stream().filter(employee -> employee.getStatus().equals(Status.FREE))
                .findAny();
        System.out.println(any.get());

    }

    @Test
    public void test2() {
        //count
        long count = employees.stream().count();
        System.out.println(count);
        //max
        Optional<Employee> max = employees.stream().max((t1, t2) -> Double.compare(t1.getSalary(), t2.getSalary()));
        System.out.println(max.get());
        //min
        Optional<Double> min = employees.stream().map(Employee::getSalary)
//                .min((t1, t2) -> Double.compare(t1, t2));
                .min(Double::compare);
        System.out.println(min.get());
    }

    /**
     * 规约  map  reduce
     */
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // reduce  先结合0作为x
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        //获取所有员工工资总和
        Optional<Double> salarySum = employees.stream()
                .map(Employee::getSalary)
//                .reduce((x, y) -> x + y);
                .reduce(Double::sum);
        System.out.println(salarySum.get());
    }

    /**
     * 收集 collect
     */
    @Test
    public void test4() {
        // 提取所有员工名字 放入集合
        List<String> nameList = employees.stream().map(Employee::getName)
                .collect(Collectors.toList());
        nameList.forEach(System.out::println);
//        System.out.println(nameList);

        //放入自定义Collection集合
        HashSet<Integer> collect = employees.stream()
                .map(Employee::getAge)
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * collectors
     * 平均值
     * 和
     * 最大最小
     */
    @Test
    public void test5() {
        //收集方法的 计算总数  个数！方法
        Long collect = employees.stream()
                .collect(Collectors.counting());

        // 工资平均值
//        Double collect1 = employees.stream()
//                .map(Employee::getSalary)
//                .collect(Collectors.averagingDouble((x) -> Double.valueOf(x)));
        Double collect1 = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect1);


        // 工资总和
        Double collect2 = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(collect2);

        // 最大值
        Optional<Employee> collect3 = employees.stream().collect(Collectors.maxBy((t1, t2) -> Double.compare(t1.getAge(), t2.getAge())));
        System.out.println(collect3.get());
    }

    /**
     * 分组
     */
    @Test
    public void test6() {
        // 按状态分组
        Map<Status, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);
        // 多列分组 ，按状态分完按年龄，20以下一组，20-40一组 40以上一组
        Map<Status, Map<String, List<Employee>>> collect1 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((employee)->{
                    if (employee.getAge()<=20) return "青年";
                    else if (employee.getAge()<=40&&employee.getAge()>20) return "中年";
                    else return "老年";
                })));
        System.out.println(collect1);
    }

    /**
     * 分区
     * 分区 满足条件的一个区，不满足的一个区
     */
    @Test
    public void test7() {
        // 满足工资大于800的一个区
        Map<Boolean, List<Employee>> collect = employees.stream()
                .collect(Collectors.partitioningBy((employee -> employee.getSalary() > 800)));
        System.out.println(collect);
    }

    /**
     *  collectors除了以上方法，还有很多其他方法获得平均值，最大最小
     */
    @Test
    public void test8() {

        DoubleSummaryStatistics collect = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getAge));
        System.out.println(collect.getAverage());
        System.out.println(collect.getMax());
        System.out.println(collect.getMin());
    }

    /**
     * join
     * 连接字符串
     */
    @Test
    public void test9() {
        String collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",","-","="));
        System.out.println(collect);
    }
}
