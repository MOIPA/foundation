package com.ds.Union;

/**
 * @author tr
 * @date 2020/12/16 20:17
 */
public class QuickFind extends UnionFind{

    public QuickFind(int capacity) {
        super(capacity);
    }

    /**
     * O(1)
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }

    /**
     * 让 v1 所在集合的所有元素都指向 v2 的根节点  下标作为实际元素值，数组内部为父节点 如 parents[2] = 1 说明 元素 2 父节点值为 1
     * O(n)
     * @param v1
     * @param v2
     */
    @Override
    public void union(int v1, int v2) {
        // 找到 v1 和 v2 的根节点
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1==p2)return;
        // 遍历parent数组, 所有父元素与v1相同的元素都需要修改为v2的父元素
        for (int i = 0; i < parents.length; i++) {
            if (parents[i]==p1)parents[i] = p2;
        }
    }

    @Override
    public boolean isSame(int v1, int v2) {
        return find(v1)==find(v2);
    }
}
