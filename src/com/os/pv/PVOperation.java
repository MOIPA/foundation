package com.os.pv;

/**
 * @author TR
 * @content 模拟PV原子操作，使用的进程挂在PVOperation对象上
 */
public class PVOperation {
    public synchronized void doP(MySemaphore semaphore) {
        --semaphore.value;
        if (semaphore.value<0){ //有线程使用中，等待，等待个数为semaphore的绝对值
            System.out.println(Thread.currentThread().getName()+" is waiting...");
            try {
                this.wait(); // 使用线程进入等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void doV(MySemaphore semaphore) {
        ++semaphore.value;
        if (semaphore.value < 0) {// 自己结束，有人等待，唤醒等待线程
            System.out.println(Thread.currentThread().getName()+" is notifying...");
            this.notify(); //唤醒一个等待线程
        }
    }
}
