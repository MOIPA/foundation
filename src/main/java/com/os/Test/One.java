package com.os.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tr
 * @date 2020/12/9 18:29
 */
public class One {
    public static void main(String[] args) {
        List<Item> list = new LinkedList<>();
        list.add(new Item("1"));
        list.add(new Item("2"));
        list.add(new Item("3"));
        list.add(new Item("x"));
        list.forEach(System.out::println);
        System.out.println("***");
        list.stream().filter(x -> x.getF() != "" && x.getF() != "x").collect(Collectors.toList()).forEach(System.out::println);
        List<Item> collect = list.stream().filter(x -> x.getF() != "" && x.getF() != "x").collect(Collectors.toList());
        System.out.println("***");
        collect.forEach(System.out::println);
    }
}
