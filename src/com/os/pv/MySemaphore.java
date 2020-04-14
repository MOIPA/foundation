package com.os.pv;

/**
 * @author TR
 * @content 信号量
 */
public class MySemaphore {
    int value = 1;

    public MySemaphore(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
