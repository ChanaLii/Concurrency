package com.mmall.concurrency;

import com.mmall.concurrency.annotions.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * @author ligy
 * @date 2018/4/6 0006 22:24
 */
@Slf4j
@NotThreadSafe
public class ConcurrencyTest {

    //请求总数
    private static int clientTotal = 5000;
    //并发执行线程数
    private static int threadTotal = 200;
    //计数器
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count{}",count);
    }

    private static void add(){
        count++;
    }
}
