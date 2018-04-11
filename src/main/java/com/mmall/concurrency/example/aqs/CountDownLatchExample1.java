package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ligy
 * @date 2018/4/11 0011 23:04
 */
@Slf4j
public class CountDownLatchExample1 {

    private static final int threadCount = 1000;

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch cdl = new CountDownLatch(threadCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {//1000个请求进来
            final int count = i;
            executorService.execute(() -> {//线程池分配任务给线程执行
                try {
                    test(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();
                }
            });
        }
        //标志所有线程执行结束
        cdl.await();
        log.info("finished");
        executorService.shutdown();
    }

    public static void test(int i) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", i);
        Thread.sleep(100);
    }
}
