package com.ds.stack_queue.array;

import com.ds.IElement;
import com.ds.stack_queue.IStack;

/**
 * @author TR
 * @content array stack
 */
public class ArrayStack implements IStack {

    private static int MAX_SIZE = 1024;

    private int top = 0;
    private Object[] stack = null;

    public ArrayStack() {
        this(MAX_SIZE);
    }

    public ArrayStack(int maxSize) {
        this.MAX_SIZE = maxSize;
        this.stack = new Object[MAX_SIZE];
    }


    @Override
    public Object pop() {
        if (this.top < 1) {
            System.out.println("empty stack failed");
            return null;
        }
        this.top--;
        return this.stack[top];
    }

    @Override
    public void push(Object value) {
        if (this.top > this.MAX_SIZE) {
            System.out.println("full failed");
            return;
        }
        this.stack[top] = value;
        this.top++;
    }

    @Override
    public void clear() {
        this.top = 0;
    }

    @Override
    public void popAll() {
        while (this.top >= 1) {
            System.out.println("value：" + (IElement) this.pop());
        }
    }

    @Override
    public int getSize() {
        return this.top;
    }
}
