package com.ds.stack_queue.array;

import com.ds.stack_queue.IQueue;

import java.text.MessageFormat;

/**
 * @author TR
 * @content array queue 循环队列
 * 思路：front指针指向空节点，front和rear同说明空，进队rear后移指向最后一个元素位置
 */
public class ArrayQueue implements IQueue {
    private static int MAX_SIZE = 5;
    private Object[] queue = null;
    private int front = 0;
    private int rear = front;

    public ArrayQueue() {
        this.queue = new Object[MAX_SIZE];
    }

    @Override
    public void clear() {
        this.front = this.rear;
    }

    @Override
    public boolean isEmpty() {
        return this.front == this.rear;
    }

    @Override
    public void enQueue(Object ele) {
        // is full
        if ((this.rear + 1) % MAX_SIZE == this.front) throw new IllegalArgumentException("full queue");
        this.rear = (this.rear + 1) % MAX_SIZE;
        queue[this.rear] = ele;
    }

    @Override
    public Object deQueue() {
        if (isEmpty()) throw new IllegalArgumentException("empty queue");
        this.front = (this.front + 1) % MAX_SIZE;
        return queue[this.front];
    }

    @Override
    public void printQueueStatus() {
        int i = this.front+1;
        int j = this.rear;
        while (i % MAX_SIZE != j+1 && !isEmpty()) {
            System.out.println(MessageFormat.format("index {0} ==> value:{1}" ,i, (int)this.queue[i]));
            i = (i+1)%MAX_SIZE;
        }
    }
}
