package com.ds.stack_queue.linked;

import com.ds.IElement;
import com.ds.stack_queue.IStack;

public class LinkedStack implements IStack {

    private Node head = null;

    public LinkedStack() {
        head = new Node(null);
    }

    @Override
    public Object pop() {
        if (head.next == null) {
            System.out.println("empty failed");
            return null;
        }
        Object ele = head.ele;
        head = head.next;
        return ele;
    }

    @Override
    public void push(Object value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
    }

    @Override
    public void clear() {
        head = new Node(null);
    }

    @Override
    public void popAll() {
        while(head.next!=null)
            System.out.println("value：" + (IElement) this.pop());
    }

    public boolean isEmpty() {
        return this.head.next==null;
    }

    public Object getTop() {
        if (isEmpty())return null;
//        Node node = head;
//        while (node.next != null) {
//            if (node.next.next==null) return node.ele;
//            node = node.next;
//        }
        return head.ele;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
