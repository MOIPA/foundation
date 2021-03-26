package com.ds.Union;

/**
 * @author tr
 * @date 2020/12/16 22:40
 */
public class Node<V> {
    public int rank = 1;
    public V value;
    public Node parent = this;
    public Node(V v) {
        this.value = v;
    }
}
