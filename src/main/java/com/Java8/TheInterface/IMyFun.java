package com.Java8.TheInterface;

/**
 * @author tr
 * @date 2020/4/14 18:16
 *
 * java8 支持接口中实现默认方法
 *
 */
public interface IMyFun {

    default String getName() {
        return "hello";
    }

}
