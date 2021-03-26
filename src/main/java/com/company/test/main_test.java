package com.company.test;

/**
 * @author tr
 * @date 2020/6/22 21:20
 */
public class main_test {

    public static void main(String[] args) {
        Liner_search a = new Liner_search();

        // 初始化数据
        Data data = Data.getInstance();
        data.setArray(new int[]{1,2,3,4,5,6});

        int b = a.linerSearch(3);
        System.out.println(b);
    }
}
