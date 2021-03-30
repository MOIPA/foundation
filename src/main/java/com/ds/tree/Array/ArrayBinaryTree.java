package com.ds.tree.Array;

import com.ds.tree.IBinaryTree;
import com.ds.tree.TNode;

/**
 * @author TR
 * @content 数组二叉树(只存储完全二叉树)，从0开始
 */
public class ArrayBinaryTree implements IBinaryTree {

    private final int MAX_SIZE = 10;
    private int size = 0;
    private Object tree[] = null;

    public ArrayBinaryTree() {
        this.tree = new Object[MAX_SIZE];
    }

    @Override
    public boolean insertNode(Object ele) {
        if (size >= MAX_SIZE) return false;
        this.tree[size++] = ele;
        return true;
    }

    @Override
    public TNode findNode(Object ele) {
        for (int i = 0; i < this.size; i++)
            if (ele.equals(tree[i])) return (TNode) tree[i];
        return null;
    }

    @Override
    public boolean deleteNode(Object ele) {
        for (int i = 0; i < this.size; i++)
            if (ele.equals(tree[i])) tree[i] = null;
        return true;
    }

    @Override
    public void preOrderPrint() {
        doPrePrint(0);
    }
    private void doPrePrint(int index) {
        if(index+1>this.size)return;
        System.out.println((String)this.tree[index]);
        doPrePrint(index*2+1);
        doPrePrint(index*2+2);
    }

    @Override
    public void infixOrderPrint() {
        doInfixPrint(0);
    }
    private void doInfixPrint(int index) {
        if(index+1>this.size)return;
        doInfixPrint(index*2+1);
        System.out.println((String)this.tree[index]);
        doInfixPrint(index*2+2);
    }

    private void doPostPrint(int index) {
        if(index+1>this.size)return;
        doPostPrint(index*2+1);
        doPostPrint(index*2+2);
        System.out.println((String)this.tree[index]);
    }

    @Override
    public void postOrderPrint() {
        doPostPrint(0);
    }

    @Override
    public void levelOrderPrint() {

    }

    @Override
    public int getNodeNumber() {
        return this.size;
    }

    @Override
    public int getLeafNumber() {
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.tree[i] != null) {
                if (i * 2 + 1 >= this.size) {
                    count++;
                    continue;
                }
            }
        }
        return count;
    }

    @Override
    public void clearTree() {

    }

    @Override
    public int getHeight() {
        int height = 1;
        int point = 0;
        while (point < this.size) {
            if (point * 2 + 1 < this.size) height++;
            point = point * 2 + 1;
        }
        return height;
    }
}

class main {
    public static void main(String[] args) {
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree();
        arrayBinaryTree.insertNode("1");
        arrayBinaryTree.insertNode("2");
        arrayBinaryTree.insertNode("3");
        arrayBinaryTree.insertNode("4");
        arrayBinaryTree.insertNode("5");
//        arrayBinaryTree.postOrderPrint();
//        arrayBinaryTree.preOrderPrint();
        arrayBinaryTree.infixOrderPrint();

    }
}
