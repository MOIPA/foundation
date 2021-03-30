package com.ds.tree;

public class TNode {
    public Object value = null;
    public TNode left = null;
    public TNode right = null;
    // 0 标识线索 1标识孩子
    public int lTag = 1;
    public int rTag = 1;

    public TNode(Object value) {
        this.value = value;
    }
    public TNode() {
    }
}
