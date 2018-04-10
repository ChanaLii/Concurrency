package com.mmall.concurrency.example.concurency;

import com.mmall.concurrency.annotions.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author ligy
 * @date 2018/4/10 0010 23:43
 */
@Slf4j
@ThreadSafe
public class CopyOnWriteArraySetExample {
    //请求总数
    private static int clientTotal = 5000;
    //并发执行线程数
    private static int threadTotal = 200;
    //计数器
    private static CopyOnWriteArraySet count = new CopyOnWriteArraySet();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int loop = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(loop);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count.size());
    }

    private static void add(int i){
        count.add(i);
    }
}
