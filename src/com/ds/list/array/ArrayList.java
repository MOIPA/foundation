package com.ds.list.array;

import com.ds.list.IList;

/**
 * @author TR
 * @Date 19/10/20
 * @content array list simulation
 */
public class ArrayList implements IList {

    private Object[] list;
    private int size = 0;

    // set a default size of list
    private static final int MAX_SIZE = 1024;

    public ArrayList() {
        this.list = new Object[MAX_SIZE];
    }

    @Override
    public void printElements() {
        for (int i = 1; i <= size; i++) printOneElement(i);
    }

    private void printOneElement(int index) {
        if (index > size) throw new ArrayIndexOutOfBoundsException("invalid index , out of size");
        System.out.println(this.list[index - 1]);
    }

    @Override
    public Integer getSize() {
        return this.size;
    }

    @Override
    public Object getValueByIndex(Integer index) {
        return list[index - 1];
    }

    @Override
    public Integer insertValueByIndex(Integer index, Object value) {
        if (size >= MAX_SIZE) throw new ArrayIndexOutOfBoundsException("list is full");

        for (int i = size; i >= index; i--) {
            list[i] = list[i - 1];
        }
        list[index - 1] = value;
        this.size++;

        return 0;
    }

    @Override
    public Integer deleteValueByIndex(Integer index) {
        return null;
    }

    @Override
    public void clearList() {
//        this.list = new Object[MAX_SIZE];
        this.size = 0;
    }

    @Override
    public Integer addValue(Object obj) {
        if (size >= MAX_SIZE) throw new ArrayIndexOutOfBoundsException("list is full");
        list[size] = obj;
        size++;
        return 0;
    }

}


