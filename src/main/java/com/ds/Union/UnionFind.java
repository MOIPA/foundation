package com.ds.Union;

/**
 * @author tr
 * @date 2019/11/26 20:10
 */
public abstract class UnionFind {
    protected int[] parents;

    public UnionFind(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be >=1");
        }
        parents = new int[capacity];
        for (int i = 0; i < parents.length; i++) {
            //parents初始化,每个元素的父元素默认为自己
            parents[i] = i;
        }
    }

    /**
     * 查找v所属的集合
     */
    public abstract int find(int v);

    /**
     * 合并v1 v2所在集合
     */
    public abstract void union(int v1, int v2);

    /**
     *  检查v1 v2是否同一集合
     */
    public abstract boolean isSame(int v1,int v2);

    /**
     * 边界检查
     */
    protected void rangeCheck(int v) {
        if (v < 0 || v >= parents.length) {
            throw new IllegalArgumentException("v is out of bounds");
        }
    }
}
