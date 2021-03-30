package com.company.threadPriority;

import java.util.concurrent.*;

public class MultiThreads {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallableThread myCallableThread = new MyCallableThread();
        FutureTask futureTask = new FutureTask<>(myCallableThread);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

        // 线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
    }
}

class MyCallableThread implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 30; i++) {
            System.out.println("doing something");
            Thread.sleep(100);
        }
        return "my callable thread";
    }
}