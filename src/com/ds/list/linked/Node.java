package com.ds.list.linked;

/**
 * @author TR
 * @content Node of linked list
 */
public class Node {

    private Object element;

    private Node next;

    public Node(Object element) {
        this.element = element;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
