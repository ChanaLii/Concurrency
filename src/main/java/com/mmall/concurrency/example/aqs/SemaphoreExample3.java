package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author ligy
 * @date 2018/4/11 0011 23:22
 */
@Slf4j
public class SemaphoreExample3 {
    private static final int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        //允许并发数为3，即初始化3个许可证书
        final Semaphore semaphore = new Semaphore(10);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < threadCount; i++) {
            final int count = i;
            executorService.execute(() -> {//线程池分配任务给线程执行
                try {
                    if (semaphore.tryAcquire()){
                        test(count);
                        semaphore.release();
                    }
                } catch (InterruptedException e) {
                   log.error("exception",e);
                }
            });
        }
        executorService.shutdown();
    }

    public static void test(int i) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", i);
    }
}
