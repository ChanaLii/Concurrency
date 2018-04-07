package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annotions.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ligy
 * @date 2018/4/7 0007 10:55
 */
@Slf4j
@ThreadSafe
public class AtomicExample6 {
    private static AtomicBoolean isHappened = new AtomicBoolean(false);
    //请求总数
    private static int clientTotal = 5000;
    //并发执行线程数
    private static int threadTotal = 200;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i <clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.info("exception",e);
                }
            });
        }
        countDownLatch.countDown();
        executorService.shutdown();
        log.info("isHappened:{}",isHappened.get());
    }

    private static void test() {
        if (isHappened.compareAndSet(false,true)){
            log.info("execute");
        }
    }
}
