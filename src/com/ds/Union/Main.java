package com.ds.Union;

/**
 * @author tr
 * @date 2020/12/16 20:40
 */
public class Main {
    public static void main(String[] args) {
//        testQuickUnionSize();
//        testQuickUnionSizeR();
        testRealUnionSizeR();
    }

    public static void testQucikFind() {
        UnionFind unionFind = new QuickFind(10);
        unionFind.union(0, 1);
        unionFind.union(2, 3);
        unionFind.union(2, 0);
        for (int i = 0; i < unionFind.parents.length; i++) {
            System.out.println("元素：" + i + " 父：" + unionFind.parents[i]);
        }
        System.out.println("find:" + unionFind.find(2));
        System.out.println("check:" + unionFind.isSame(2, 4));
    }

    public static void testQucikUnion() {
        UnionFind unionFind = new QuickUnion(10);
        unionFind.union(0, 1);
        unionFind.union(2, 3);
        unionFind.union(2, 0);
        for (int i = 0; i < unionFind.parents.length; i++) {
            System.out.println("元素：" + i + " 父：" + unionFind.parents[i]);
        }
        System.out.println("find:" + unionFind.find(2));
        System.out.println("check:" + unionFind.isSame(2, 3));
    }

    public static void testQuickUnionSize() {
        UnionFind_QU_S unionFind = new UnionFind_QU_S(10);
        unionFind.union(0, 2);
        unionFind.union(1, 0);
        unionFind.union(1, 3);
        unionFind.find(1);
        for (int i = 0; i < unionFind.parents.length; i++) {
            System.out.println("元素：" + i + " 父：" + unionFind.parents[i]);
        }
        System.out.println("find:" + unionFind.find(2));
        System.out.println("check:" + unionFind.isSame(2, 4));
    }
    public static void testQuickUnionSizeR() {
        UnionFind_QU_R unionFind = new UnionFind_QU_R(8);
        unionFind.union(1, 2);
        unionFind.union(2, 3);
        unionFind.union(3, 4);
        unionFind.union(4, 5);
        unionFind.union(5, 6);
        unionFind.union(6, 7);
//        unionFind.findPathSpliting(1);
        unionFind.findPathHalving(1);
        for (int i = 0; i < unionFind.parents.length; i++) {
            System.out.println("元素：" + i + " 父：" + unionFind.parents[i]);
        }
    }

    public static void testRealUnionSizeR() {
        GenericUnionFind<Integer,String> unionFind = new GenericUnionFind<>();
        unionFind.makeSet(1,"a");
        unionFind.makeSet(2,"b");
        unionFind.makeSet(3,"c");
        unionFind.makeSet(4,"d");
        unionFind.makeSet(5,"e");
        unionFind.union(1,2);
        unionFind.union(1,5);
        unionFind.union(3,4);
        System.out.println(unionFind.isSame(1,3));
        System.out.println(unionFind.isSame(2,5));
        System.out.println(unionFind.find(5));
    }
}
