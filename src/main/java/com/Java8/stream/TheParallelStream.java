package com.Java8.stream;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @author tr
 * @date 2020/4/14 16:38
 *
 * 并行流
 *
 */
public class TheParallelStream {

    /**
     * 流中使用parallel方法可以切换为parallel
     */
    @Test
    public void test1() {
        Instant start = Instant.now();
        LongStream.rangeClosed(0, 100000000000L)
                .parallel()
                .reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start,end).getSeconds());
    }
}
