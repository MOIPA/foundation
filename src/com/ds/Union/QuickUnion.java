package com.ds.Union;

/**
 * @author tr
 * @date 2020/12/16 20:46
 */
public class QuickUnion extends UnionFind{

    public QuickUnion(int capacity) {
        super(capacity);
    }

    /**
     * O(logN)
     * @param v
     * @return
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (parents[v] != v) {
            v=parents[v];
        }
        return v;
    }

    /**
     * Quick Union 的 union(v1, v2)：让 v1 的根节点指向 v2 的根节点
     * O(logN)
     */
    @Override
    public void union(int v1, int v2) {
       // 找到 两者根节点
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1==p2)return;
        parents[p1] = p2;
    }

    @Override
    public boolean isSame(int v1, int v2) {
        return find(v1)==find(v2);
    }
}
