package com.ds.tree;

public interface IBinaryTree {
    boolean insertNode(Object ele);

    TNode findNode(Object ele);

    boolean deleteNode(Object ele);

    void preOrderPrint();

    void infixOrderPrint();

    void postOrderPrint();

    void levelOrderPrint();

    int getNodeNumber();

    int getLeafNumber();

    void clearTree();

    int getHeight();
}
