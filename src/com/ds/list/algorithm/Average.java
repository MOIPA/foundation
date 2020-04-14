package com.ds.list.algorithm;

import com.ds.list.linked.LinkedList;
import com.ds.list.linked.Node;

public class Average {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addValue("3");
        linkedList.addValue("1");
        linkedList.addValue("2");
        linkedList.addValue("3");
        linkedList.addValue("4");
        linkedList.addValue("5");
        System.out.println("total is :"+getAverage(linkedList.getFirstNode()));
        System.out.println("numbers is :"+getTotal(linkedList.getFirstNode()));
    }

    public static int getAverage(Node node) {
        if (node==null)return 0;
        return Integer.parseInt((String) node.getElement())+getAverage(node.getNext());
    }

    public static int getTotal(Node node) {
        if (node==null)return 0;
        return 1 + getTotal(node.getNext());
    }
}
