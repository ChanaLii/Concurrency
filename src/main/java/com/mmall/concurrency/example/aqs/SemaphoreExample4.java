package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author ligy
 * @date 2018/4/11 0011 23:22
 */
@Slf4j
public class SemaphoreExample4 {
    private static final int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        //允许并发数为3，即初始化3个许可证书
        final Semaphore semaphore = new Semaphore(3);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < threadCount; i++) {
            final int count = i;
            executorService.execute(() -> {//线程池分配任务给线程执行
                try {
                    //在给定的等待时间内，此信号量有可用的许可证并且当前线程未中断，则从此信号量获取一个许可
                    if (semaphore.tryAcquire(500, TimeUnit.MILLISECONDS)){
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
