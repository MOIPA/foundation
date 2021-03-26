package com.ds.Union;

/**
 * @author tr
 * @date 2020/12/16 20:57
 */

/**
 * quickUnion 容易退化成链表
 * 基于size的优化：元素少的树 嫁接到 元素多的树
 */
public class UnionFind_QU_S extends QuickUnion {
    private int[] sizes;

    public UnionFind_QU_S(int capacity) {
        super(capacity);
        // 保存节点数，下标为节点，值为节点下挂的元素个数，每次执行union时对应父节点增加
        sizes = new int[capacity];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = 1;
        }
    }

    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        // 判断哪个元素所在的集合树的节点数量比较少, 那么就将该树嫁接到节点数量较多的树上
        if (sizes[p1] > sizes[p2]) {
            parents[p2] = p1;
            sizes[p1] = sizes[p2] + sizes[p1];
        }else {
            parents[p1] = p2;
            sizes[p2] = sizes[p2] + sizes[p1];
        }

    }
}
