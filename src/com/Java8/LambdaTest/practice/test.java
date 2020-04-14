package com.Java8.LambdaTest.practice;

import org.junit.Test;

public class test {
    @Test
    public void test1() {
        int num = 10;
//        MyFunction myFunction = (x)->(Integer) x*(Integer)x;
        Integer operate = operate(num, x ->  x * x);
        System.out.println(operate);
    }

    @Test
    public void test2() {
        System.out.println(operate((long)19, (long)12, (x1, x2) -> x1 + x2));
    }

    private Integer operate(Integer num, MyFunction<Integer> myFunction) {
        return myFunction.calc(num);
    }

    private Long operate(Long num1, Long num2, MyFunctionTwo<Long, Long> myFunctionTwo) {
        return myFunctionTwo.calc(num1, num2);
    }
    
}
