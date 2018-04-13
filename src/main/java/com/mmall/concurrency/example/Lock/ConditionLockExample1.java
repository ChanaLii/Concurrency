package com.mmall.concurrency.example.Lock;

import lombok.extern.slf4j.Slf4j;
import sun.misc.ConditionLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ligy
 * @date 2018/4/13 0013 22:26
 */
@Slf4j
public class ConditionLockExample1 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();
                log.info("wait signal");
                condition.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("get signal");
            reentrantLock.unlock();
        }).start();

        new Thread(()->{
            reentrantLock.lock();
            log.info("get lock");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("send signal!");
            reentrantLock.unlock();
        }).start();
    }
}

