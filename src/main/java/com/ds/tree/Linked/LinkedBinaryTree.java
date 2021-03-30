package com.ds.tree.Linked;

import com.ds.stack_queue.array.ArrayQueue;
import com.ds.stack_queue.linked.LinkedStack;
import com.ds.tree.IBinaryTree;
import com.ds.tree.TNode;

/**
 * @author TR
 * @content 关于前中后序遍历和非递归用栈的遍历，层次遍历（队列），线索化，使用树做表达式计算
 * 交换左右子树
 */
public class LinkedBinaryTree implements IBinaryTree {
    private TNode root = null;

    public LinkedBinaryTree(Object value) {
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

    public boolean deleteNode(TNode ele) {
        if (ele == root) return false;
        return doDeleteNode(this.root, ele);
    }

    private boolean doDeleteNode(TNode node, TNode ele) {
        if (node == null) return false;
        if (node.left == ele) {
            node.left = null;
            return true;
        } else if (node.right == ele) {
            node.right = null;
            return true;
        } else {
            if (doDeleteNode(node.left, ele)) return true;
            return doDeleteNode(node.right, ele);
        }
    }

    private int visitResult(TNode ele) {
        System.out.println(ele.value);
        return 0;
    }

    private void doPreOrder(TNode node) {
        if (node == null) return;
        visitResult(node);
        doPreOrder(node.left);
        doPreOrder(node.right);
    }

    private void doInfixOrder(TNode node) {
        if (node == null) return;
        doInfixOrder(node.left);
        visitResult(node);
        doInfixOrder(node.right);
    }

    private void doPostOrder(TNode node) {
        if (node == null) return;
        doPostOrder(node.left);
        doPostOrder(node.right);
        visitResult(node);
    }

    public int calcTree() {
        return Integer.parseInt(doCalc(this.root));
    }

    public String doCalc(TNode node) {
        if (node.left != null && node.right != null) {
            //expression
            return calc(doCalc(node.left), doCalc(node.right), (String) node.value) + "";
        }
        return (String) node.value;
    }

    public int calc(String a, String b, String o) {
        switch (o) {
            case "+":
                return Integer.parseInt(a) + Integer.parseInt(b);
            case "-":
                return Integer.parseInt(a) - Integer.parseInt(b);
            case "*":
                return Integer.parseInt(a) * Integer.parseInt(b);
            case "/":
                return Integer.parseInt(a) / Integer.parseInt(b);
        }
        return 0;
    }

    @Override
    public void preOrderPrint() {
        doPreOrder(this.root);
    }

    @Override
    public void infixOrderPrint() {
        doInfixOrder(this.root);
    }

    @Override
    public void postOrderPrint() {
        doPostOrder(this.root);
    }

    @Override
    public void levelOrderPrint() {
        ArrayQueue arrayQueue = new ArrayQueue();
        arrayQueue.enQueue(this.root);
        while (!arrayQueue.isEmpty()) {
            Object o = arrayQueue.deQueue();
            visitResult((TNode) o);
            if (((TNode) o).left != null) arrayQueue.enQueue(((TNode) o).left);
            if (((TNode) o).right != null) arrayQueue.enQueue(((TNode) o).right);
        }
    }

    public void preOrderPrintWithStack() {
        // 实现思路：根入栈，出栈，访问节点，将左右入栈，出栈...
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push(this.root);
        while (!linkedStack.isEmpty()) {
            TNode node = (TNode) linkedStack.pop();
            visitResult(node);
            if (node.right != null) linkedStack.push(node.right);
            if (node.left != null) linkedStack.push(node.left);
        }
    }

    public void infixOrderPrintWhitStack() {
        // 中序遍历：头入栈，出栈，检测是否有左节点，有入栈……
        // 无左节点，出栈，访问，查看有无右节点，有入栈
        // 使用状态位防止无限循环入栈，状态位标识：1表示处于进栈判断中，0表示进栈结束，出栈中
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push(this.root);
        int outStack = 1;
        while (!linkedStack.isEmpty()) {
            TNode node = (TNode) linkedStack.getTop();
            if (node.left != null && outStack == 1) {
                linkedStack.push(node.left);
            } else {
                node = (TNode) linkedStack.pop();
                outStack = 0;
                visitResult(node);
                if (node.right != null) {
                    linkedStack.push(node.right);
                    outStack = 1;
                }
            }
        }
    }

    public void reverseTree() {
        doReverse(this.root);
    }

    private void doReverse(TNode node) {
        //交换本节点左右树，分别进入节点再换
        if (node==null) return;
        TNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        doReverse(node.left);
        doReverse(node.right);
    }

    public void postOrderPrintWithStack() {
        // 逆后续遍历 根-右左
        // 先序遍历，根左右，所以在先序遍历上修改，交换左右入栈次序，再用栈逆序即可得后序遍历
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push(this.root);
        LinkedStack outStack = new LinkedStack();
        while (!linkedStack.isEmpty()) {
            TNode pop = (TNode) linkedStack.pop();
            outStack.push(pop);
            if (pop.left != null) linkedStack.push(pop.left);
            if (pop.right != null) linkedStack.push(pop.right);
        }
        while (!outStack.isEmpty()) {
            visitResult((TNode) outStack.pop());
        }
    }

    public void threadlize() {

    }

    public void doTread(TNode node, TNode preNode) {
        //preNode:标识上次访问的节点
        if (node == null || node.lTag == 0) return;
        //访问左节点
        doTread(node.left, preNode);
        //访问本次节点 接入前节点和后节点
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
        doTread(node.right, preNode);
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
        return doCalcHeight(this.root);
    }

    private int doCalcHeight(TNode node) {
        if (node == null) return 0;
        int leftHeight = doCalcHeight(node.left);
        int rightHeight = doCalcHeight(node.right);
        return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);

    }
}

class main {
    public static void main(String[] args) {
        /**
         * *
         * -    /
         * 6 +  4 2
         *   2 1
         */
        LinkedBinaryTree linkedBinaryTree = new LinkedBinaryTree("*");
        TNode root = linkedBinaryTree.getRoot();
        TNode left = linkedBinaryTree.insertLeft(root, new TNode("-"));
        TNode right = linkedBinaryTree.inserRight(root, new TNode("/"));

        linkedBinaryTree.insertLeft(right, new TNode("4")); // d
        linkedBinaryTree.inserRight(right, new TNode("2")); //e

        linkedBinaryTree.insertLeft(left, new TNode("6")); // a
        left = linkedBinaryTree.inserRight(left, new TNode("+"));

        linkedBinaryTree.insertLeft(left, new TNode("2"));  //b
        left = linkedBinaryTree.inserRight(left, new TNode("1")); //c
//        linkedBinaryTree.deleteNode(left);

//        linkedBinaryTree.postOrderPrint();
//        System.out.println(linkedBinaryTree.calcTree());
//        System.out.println(linkedBinaryTree.getHeight());
//        linkedBinaryTree.levelOrderPrint();

//        linkedBinaryTree.postOrderPrint();
//        System.out.println("*******");
//        linkedBinaryTree.postOrderPrintWithStack();

        linkedBinaryTree.reverseTree();
        linkedBinaryTree.levelOrderPrint();

    }
}
