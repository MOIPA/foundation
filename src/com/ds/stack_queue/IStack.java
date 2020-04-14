package com.ds.stack_queue;

public interface IStack {
    Object pop();

    void push(Object value);

    void clear();

    void popAll();

    int getSize();
}
