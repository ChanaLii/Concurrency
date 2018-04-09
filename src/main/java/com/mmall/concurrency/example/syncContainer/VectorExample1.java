package com.mmall.concurrency.example.syncContainer;

import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author ligy
 * @date 2018/4/9 0009 22:15
 */
@Slf4j
public class VectorExample1 {

    private static final int CLIENT_TOTAL  = 5000;

    private static final int THREAD_TOTAL = 200;

    private static Vector<Integer> list = new Vector<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_TOTAL);
        final CountDownLatch countDownLatch = new CountDownLatch(CLIENT_TOTAL);
        for (int i = 0; i < CLIENT_TOTAL; i++) {
            final int count = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    list.add(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.countDown();
        executorService.shutdown();
        log.info("{}",list.size());
    }
}
