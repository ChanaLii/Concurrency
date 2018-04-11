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
public class SemaphoreExample2 {
    private static final int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(3);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            final int count = i;
            executorService.execute(() -> {//线程池分配任务给线程执行
                try {
                    //获取多个许可
                    semaphore.acquire(3);
                    test(count);
                    //释放多个许可
                    semaphore.release(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
