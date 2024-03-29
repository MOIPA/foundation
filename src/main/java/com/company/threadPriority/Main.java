package com.company.threadPriority;

public class Main {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("Main Thread  Priority:" + t.getPriority());

        Thread t1 = new Thread();
        System.out.println("Thread(t1) Priority:" + t1.getPriority());
        t1.setPriority(Thread.MAX_PRIORITY - 1);
        System.out.println("Thread(t1) Priority:" + t1.getPriority());

        t.setPriority(Thread.NORM_PRIORITY);
        System.out.println("Main Thread  Priority:" + t.getPriority());

        Thread t2 = new Thread();
        System.out.println("Thread(t2) Priority:" + t2.getPriority());

        // Change thread t2 priority to minimum
        t2.setPriority(Thread.MIN_PRIORITY);
        System.out.println("Thread(t2) Priority:" + t2.getPriority());

    }
}
