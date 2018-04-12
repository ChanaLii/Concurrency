package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ligy
 * @date 2018/4/12 0012 22:15
 */
@Slf4j
public class CyclicBarrierExample1 {
    private static int threadCount = 20;

    //设置等待线程数为5
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            final int count = i;
            Thread.sleep(1000);
            executor.execute(() -> {
                try {
                    race(count);
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        executor.shutdown();
    }

    private static void race(int threadCount) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(1000);
        log.info("thread ready:{}", threadCount);
        //线程等待，给定的线程数量就绪后向下执行
        cyclicBarrier.await();
        log.info("thread run:{}",threadCount);
    }
}
