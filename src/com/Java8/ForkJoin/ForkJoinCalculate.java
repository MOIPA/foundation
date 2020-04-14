package com.Java8.ForkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author tr
 * @date 2020/4/14 16:21
 *
 * FORK JOIN 模式  麻烦 现在基本没人用
 *
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private static final long serialVersionUID = 1345671232123L;

    private long start;
    private long end;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    // 拆到1w为止
    private static final long THERESHOLD = 10000;

    @Override
    protected Long compute() {
        long length = end-start;
        if (length <= THERESHOLD) {
            long sum = 0;
            for (long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        }else{
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();  //拆分子任务，同时压入线程队列
            ForkJoinCalculate right = new ForkJoinCalculate(middle+1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
