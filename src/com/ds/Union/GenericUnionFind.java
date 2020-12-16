package com.ds.Union;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author tr
 * @date 2020/12/16 22:39
 * @param <K> 索引类型
 * @param <V> 实际存储类型
 */

public class GenericUnionFind<K,V> {
    private Map<K, Node<V>> nodes = new HashMap<>();

    public void makeSet(K k, V v) {
        if (nodes.containsKey(v)) return;
        nodes.put(k, new Node<>(v));
    }

    /**
     * 找出V的根节点
     */
    private Node<V> findRoot(K k) {
        Node<V> node = nodes.get(k);
        if (node==null)return null;
        while(!Objects.equals(node.value, node.parent.value)){
            node.parent = node.parent.parent;
            node = node.parent;
        }
        return node;
    }

    public V find(K v) {
        Node<V> node = findRoot(v);
        return node == null ? null : node.value;
    }

    public void union(K v1, K v2) {
        Node<V> p1 = findRoot(v1);
        Node<V> p2 = findRoot(v2);
        if (p1==null||p2==null)return;
        if (Objects.equals(p1.value,p2.value))return;

        if (p1.rank < p2.rank) {
            p1.parent = p2;
        } else if (p1.rank > p2.rank) {
            p2.parent = p1;
        } else {
            p1.parent = p2;
            p2.rank++;
        }
    }

    public boolean isSame(K v1, K v2) {
        return Objects.equals(find(v1), find(v2));
    }
}
