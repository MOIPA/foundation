package com.ds;

import com.ds.list.IList;
import com.ds.list.array.ArrayList;
import com.ds.list.linked.LinkedList;
import com.ds.list.linked.LinkedListWithHead;
import com.ds.stack_queue.IQueue;
import com.ds.stack_queue.IStack;
import com.ds.stack_queue.array.ArrayQueue;
import com.ds.stack_queue.array.ArrayStack;
import com.ds.stack_queue.linked.LinkedStack;

public class Main {

    public static void main(String[] args) {
//        ListUseTest test = new ListUseTest("array list");
//        ListUseTest test = new ListUseTest("linked list wh");
//        ListUseTest test = new ListUseTest("linked list");
//        test.doTest();

//        StackUseTest stackUseTest = new StackUseTest("array stack");
//        StackUseTest stackUseTest = new StackUseTest("linked stack");
//        stackUseTest.doTest();

        QueueUseTest queueUseTest = new QueueUseTest();
        queueUseTest.arrayTest(new ArrayQueue());
    }
}

class ListUseTest {
    private IList list;

    public ListUseTest(String type) {
        if (type.equals("array list")) list = new ArrayList();
            //TODO new linked list
        else if (type.equals("linked list wh")) {
            list = new LinkedListWithHead();
        } else if (type.equals("linked list")) {
            list = new LinkedList();
        }
    }

    public void doTest() {
        list.addValue(new Element(1, "测试"));
        list.addValue(new Element(2, "测试"));
        list.addValue(new Element(3, "测试"));
        list.addValue(new Element(4, "测试"));
        list.insertValueByIndex(2, new Element(-1, "插入者"));
//        list.deleteValueByIndex(2);
        list.printElements();
        System.out.println(list.getSize());
    }
}

class StackUseTest {

    private IStack stack;

    public StackUseTest(String type) {
        if (type.equals("array stack")) {
            stack = new ArrayStack();
        } else if (type.equals("linked stack")) {
            stack = new LinkedStack();
        }
    }

    public void doTest() {
        stack.push(new Element(3, ""));
        stack.push(new Element(2, ""));
        stack.push(new Element(1, ""));
        System.out.println("pop one" + stack.pop());
        stack.push(new Element(4, ""));
        System.out.println("size" + stack.getSize());
        stack.popAll();
    }

}

class QueueUseTest {

    public void arrayTest(IQueue iQueue) {
        iQueue.enQueue(1);
        iQueue.enQueue(2);
        iQueue.enQueue(3);
        iQueue.enQueue(4);
        System.out.println("out:"+(int)iQueue.deQueue());
        System.out.println("out:"+(int)iQueue.deQueue());
        iQueue.enQueue(5);
        iQueue.enQueue(6);
        iQueue.printQueueStatus();
    }

}
