package com.os.consumerproductor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author tr
 * @date 2020/12/9 17:43
 *
 * 思路：定义total 存放全部生产者数据，生产者生成随机数，随机存入数组，消费者获取生产者的随机数，调用search方法查找下标
 */
public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        repository.initTotal();
        for (int i = 0; i < 20; i++) {
            new Thread(new Producer(repository, 1)).start();
        }
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Consumer(repository, 1)).start();
        }
    }
}

class Producer implements Runnable {

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private Repository repository = null;
    private int num;

    @Override
    public void run() {
        try {
            repository.producer(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Producer(Repository repository, int num) {
        this.repository = repository;
        this.num = num;
    }
}

class Consumer implements Runnable {

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setNum(int num) {
        this.num = num;
    }

    private Repository repository = null;
    private int num;

    @Override
    public void run() {
        try {
            repository.consume(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Consumer(Repository repository, int num) {
        this.repository = repository;
        this.num = num;
    }
}

class Repository {
    private final static int[] total = new int[100];
    private final static int MAX = 10;
    private List<Integer> items = new LinkedList<>();

    // 初始化200个数
    public void initTotal() {
        for (int i = 0; i < 100; i++) {
            total[i] = -1;
        }
    }

    // 二分查找法
    public static int search(int target, int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == target) return i;
        return -1;
    }

    public void consume(int num) throws InterruptedException {
        synchronized (items) {
            while (num > items.size()) {
                items.wait();
            }
            for (int i = 0; i < num; i++) {
                int remove = items.remove(0);
                System.out.println("消费者：生产的数:" + remove+"下标："+search(remove,total));
                Thread.sleep(200);
                items.notifyAll();
            }
        }
    }

    public void producer(int num) throws InterruptedException {
        synchronized (items) {
            while (items.size() + num > MAX) {
                // 无法生产
                items.wait();
            }
            for (int i = 0; i < num; i++) {
                Thread.sleep(200);
                // 生产的随机数
                int temp = (int) (Math.random() * 200 % 200);
                total[(int)(Math.random() * 100 % 100)] = temp;
                items.add(temp);
                System.out.println("生产者生产一个数:" + temp);
            }
            items.notifyAll();
        }
    }
}

