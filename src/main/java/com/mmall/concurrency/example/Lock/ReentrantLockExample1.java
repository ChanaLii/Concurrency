package com.mmall.concurrency.example.Lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁：先等待先出列
 * 不公平锁：插队运行
 * @author ligy
 * @date 2018/4/12 0012 23:41
 */
@Slf4j
public class ReentrantLockExample1 {
    //请求总数
    private static int clientTotal = 5000;
    //并发执行线程数
    private static int threadTotal = 200;
    //计数器
    private static int count = 0;

    private static Lock lock = new ReentrantLock();

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
        log.info("count:{}",count);
    }
    //对操作方法进行lock
    private static void add(){
        lock.lock();
        try{
            count++;
        }finally {
            lock.unlock();
        }
    }
}
