package com.ds.list.linked;

import com.ds.list.IList;

/**
 * @author TR
 * @conten linked list without head node
 */
public class LinkedListWithHead implements IList {

    private Node headNode = null;

    // initiate the head node
    public LinkedListWithHead() {
        headNode = new Node(null);
        headNode.setNext(null);
        headNode.setElement(null);
    }

    @Override
    public void printElements() {
        Node next = headNode.getNext();
        while (next != null) {
            System.out.println(next.getElement());
            next = next.getNext();
        }
    }

    @Override
    public Integer getSize() {
        Node nextNode = headNode.getNext();
        int count = 0;
        while (nextNode != null) {
            count++;
            nextNode = nextNode.getNext();
        }
        return count;
    }

    @Override
    public Object getValueByIndex(Integer index) {
        int count=0;
        Node nextNode = headNode.getNext();
        while (nextNode != null) {
            count++;
            if (count == index) return nextNode;
            nextNode = nextNode.getNext();
        }
        throw new IllegalArgumentException("out of index");
    }

    @Override
    public Integer insertValueByIndex(Integer index, Object value) {
        // key point
        int count=0;
        Node insertNode = new Node(value);
        Node nextNode = headNode;
        while (nextNode != null) {
            count++;
            if (index == count) break;
            nextNode = nextNode.getNext();
        }
        insertNode.setNext(nextNode.getNext());
        nextNode.setNext(insertNode);
        return 0;
    }

    @Override
    public Integer deleteValueByIndex(Integer index) {
        int count=-1;
        Node next = headNode;
        while (next != null) {
            count++;
            if (index - 1 == count)
                break;
            next = next.getNext();
        }
        next.setNext(next.getNext().getNext());
        return 0;
    }

    @Override
    public void clearList() {
        headNode = new Node(null);
    }

    @Override
    public Integer addValue(Object obj) {
        Node next = headNode;
        while (next.getNext() != null) {
            next = next.getNext();
        }
        next.setNext(new Node(obj));
        return 0;
    }
}
