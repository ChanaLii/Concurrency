package com.mmall.concurrency.example.aqs;

import ch.qos.logback.core.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author ligy
 * @date 2018/4/11 0011 23:05
 */
@Slf4j
public class CountDownLatchExample2 {

    private static final int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch cdl = new CountDownLatch(threadCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {//200个请求进来
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
        //CountDownLatch等待10毫秒，以上线程仍在执行但不再等待，马上向下执行；以上线程未执行结束的继续执行到结束为止。
        cdl.await(10, TimeUnit.MILLISECONDS);
        log.info("finished");
        executorService.shutdown();
    }

    public static void test(int i) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", i);
    }
}
