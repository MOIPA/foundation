package com.ds.Union;

/**
 * @author tr
 * @date 2020/12/16 21:23
 */
public class UnionFind_QU_R extends QuickUnion {
    private int[] ranks;

    public UnionFind_QU_R(int capacity) {
        super(capacity);
        // 每棵树的高度
        ranks = new int[capacity];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = 1;
        }
    }

    /**
     * 在find过程, 将find路劲上的所有元素都指向根节点, 达到压缩路径的作用
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        if (v != parents[v]) {
            //非根节点  将自己的父节点指向根节点用于压缩路径
            parents[v] = find(v);
            //TODO 降低树高
        }
        return v;
    }

    /**
     * 路径压缩使路径上的所有节点都指向根节点，所以实现成本稍高,还有2种更优的做法，
     * 不但能降低树高，实现成本也比路径压缩低:路径分裂(Path Spliting), 路径减半(Path Halving);
     * 路径分裂、路径减半的效率差不多，但都比路径压缩要好
     * <p>
     * 路径分裂 (Path Spliting)
     */
    public int findPathSpliting(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            int p = parents[v];
            parents[v] = parents[parents[v]];
            v = p;
        }
        return v;
    }

    /**
     * 路径减半（Path Halving）
     * 路径减半：使路径上每隔一个节点就指向其祖父节点(parent的parent)
     */
    public int findPathHalving(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }

    /**
     * 矮的树嫁接到高的树上
     */
//    @Override
//    public void union(int v1, int v2) {
//        int p1 = find(v1);
//        int p2 = find(v2);
//        if (p1 == p2) return;
//        /**
//         * 判断哪个元素所在的集合树比较矮, 那么就将该树嫁接到较高的树上
//         * 如果两个树高度不同, 那么嫁接后, 两个树的高度都未发生改变
//         * 如果两个树的高度一致, 那么嫁接后, 矮的树高度不变, 高的树高度加 1
//         */
//        if (ranks[p1] > ranks[p2]) {
//            parents[p2] = p1;
//        } else if (ranks[p1] < ranks[p2]) {
//            parents[p1] = p2;
//        } else {
//            parents[p2] = p1;
//            ranks[p1]++;
//        }
//    }
}
