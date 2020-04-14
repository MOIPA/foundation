package com.ds.list.linked;

import com.ds.list.IList;

public class LinkedList implements IList {

    private Node head = null;

    public LinkedList() {
    }

    @Override
    public void printElements() {
        Node node = head;
        while (node != null) {
            System.out.println(node.getElement());
            node = node.getNext();
        }
    }

    @Override
    public Integer getSize() {
        int count=0;
        Node node = head;
        while (node != null) {
            count++;
            node = node.getNext();
        }
        return count;
    }

    @Override
    public Object getValueByIndex(Integer index) {
        Node node = head;
        int count=0;
        while (node != null) {
            count++;
            if (index == count) {
                return node.getElement();
            }
            node = node.getNext();
        }
        return null;
    }

    @Override
    public Integer insertValueByIndex(Integer index, Object value) {
        Node insertNode = new Node(value);
        if (index == 1) {
            insertNode.setNext(head);
            head = insertNode;
            return 0;
        }
        int count=0;
        Node node = head;
        while (node != null) {
            count++;
            if (count == index - 1) {
                break;
            }
            node = node.getNext();
        }
        insertNode.setNext(node.getNext());
        node.setNext(insertNode);
        return null;
    }

    @Override
    public Integer deleteValueByIndex(Integer index) {
        if (1 == index) {
            head = head.getNext();
            return 0;
        }
        Node node = locateNode(index - 1);
        node.setNext(node.getNext().getNext());
        return null;
    }

    private Node locateNode(int index) {
        int count = 0;
        Node node = head;
        while (node != null) {
            count++;
            if (count == index) {
                break;
            }
            node = node.getNext();
        }
        return node;
    }

    @Override
    public void clearList() {
        this.head = null;
    }

    @Override
    public Integer addValue(Object obj) {
        Node node = this.head;
        if (head == null) {
            this.head = new Node(obj);
            return null;
        }
        while (node.getNext() != null) {
            node = node.getNext();
        }
        node.setNext(new Node(obj));
        return null;
    }

    public Node getFirstNode() {
        return head;
    }
}
