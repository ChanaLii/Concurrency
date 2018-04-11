package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author ligy
 * @date 2018/4/11 0011 23:22
 */
@Slf4j
public class SemaphoreExample1 {
    private static final int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(3);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            final int count = i;
            executorService.execute(() -> {//线程池分配任务给线程执行
                try {
                    //获取一个许可
                    semaphore.acquire();
                    test(count);
                    //释放一个许可
                    semaphore.release();
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
