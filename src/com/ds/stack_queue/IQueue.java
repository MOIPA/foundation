package com.ds.stack_queue;

public interface IQueue {
    void clear();

    boolean isEmpty();

    void enQueue(Object ele);

    Object deQueue();

    void printQueueStatus();
}
