package com.ds.tree.Linked;

import com.ds.tree.IBinaryTree;
import com.ds.tree.TNode;

/**
 * @author TR
 * @content 线索化
 */
public class ThreadLinkedBinaryTree implements IBinaryTree {
    private TNode root = null;
    private TNode preNode = null;

    public ThreadLinkedBinaryTree(Object value) {
        root = new TNode(value);
    }

    public TNode getRoot() {
        return this.root;
    }

    @Override
    public boolean insertNode(Object ele) {
        return false;
    }

    public TNode insertLeft(TNode node, TNode ele) {
        node.left = ele;
        return ele;
    }

    public TNode inserRight(TNode node, TNode ele) {
        node.right = ele;
        return ele;
    }

    @Override
    public TNode findNode(Object ele) {
        return null;
    }

    @Override
    public boolean deleteNode(Object ele) {
        return false;
    }

    private int visitResult(TNode ele) {
        System.out.println(ele.value);
        return 0;
    }

    @Override
    public void preOrderPrint() {
    }

    @Override
    public void infixOrderPrint() {
    }

    @Override
    public void postOrderPrint() {
    }

    @Override
    public void levelOrderPrint() {
    }


    public void threadlize() {
        doTread(this.root);
    }

    public void doTread(TNode node) {
        //preNode:标识上次访问的节点
        if (node == null || node.lTag == 0) return;
        //访问左节点
        doTread(node.left);
        //访问本次节点 接入前节点和后节点
        if (node.value.equals("*")) {
            System.out.println();
        }
        if (preNode != null && preNode.right == null) {
            preNode.right = node;
            preNode.rTag = 0;
        }
        if (node.left == null) {
            node.left = preNode;
            node.lTag = 0;
        }
        preNode = node;
        // 访问右节点
        doTread(node.right);
    }

    // 线索化后的第一个线索节点
    public TNode findFirstNode(TNode node) {
        while (node != null) {
            if (node.lTag==0)return node;
            if (node.left==null)return node;
            node = node.left;
        }
        return node;
    }

    // 线索化后的某节点的后一个节点
    public TNode findNextNode(TNode node) {
        if (node.rTag==0) return node.right;
        return findFirstNode(node.right);
    }

    // 线索化后的遍历
    public void travel() {
        TNode node = findFirstNode(this.root);
        while (node != null) {
            visitResult(node);
            node = findNextNode(node);
        }
    }

    @Override
    public int getNodeNumber() {
        return 0;
    }

    @Override
    public int getLeafNumber() {
        return 0;
    }

    @Override
    public void clearTree() {

    }

    @Override
    public int getHeight() {
        return -1;
    }

}

class test {
    public static void main(String[] args) {
        ThreadLinkedBinaryTree linkedBinaryTree = new ThreadLinkedBinaryTree("*");
        TNode root = linkedBinaryTree.getRoot();
        TNode left = linkedBinaryTree.insertLeft(root, new TNode("-"));
        TNode right = linkedBinaryTree.inserRight(root, new TNode("/"));

        linkedBinaryTree.insertLeft(right, new TNode("d")); // d
        linkedBinaryTree.inserRight(right, new TNode("e")); //e

        linkedBinaryTree.insertLeft(left, new TNode("a")); // a
        left = linkedBinaryTree.inserRight(left, new TNode("+"));

        linkedBinaryTree.insertLeft(left, new TNode("b"));  //b
        left = linkedBinaryTree.inserRight(left, new TNode("c")); //c

        // doThread 操作 线索化
        linkedBinaryTree.threadlize();
        linkedBinaryTree.travel();

    }
}
