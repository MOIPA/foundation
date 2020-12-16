package com.test;

/**
 * @author tr
 * @date 2020/6/22 21:19
 */
public class Liner_search {
    public int linerSearch(int searchKey) {
        Data data = Data.getInstance();
        int[] array = data.getArray();
        for (int index = 0; index < array.length; index++) {
            return index;
        }
        return -1;
    }
}
