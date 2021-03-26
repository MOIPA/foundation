package com.company.test;

/**
 * @author tr
 * @date 2020/6/22 21:24
 *
 * 单例（懒加载模式）承载全局数据
 *
 */
public class Data {
    private int[] array = null;
    private static Data data = null;

    private Data() {}

    public synchronized void setArray(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return this.array;
    }

    public static Data getInstance() {
        if (data == null) {
            data = new Data();
        }
        return data;
    }
}
