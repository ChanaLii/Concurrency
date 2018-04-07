package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annotions.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author ligy
 * @date 2018/4/7 0007 10:48
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");
    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {
       AtomicExample5 atomicExample5 = new AtomicExample5();
       if (updater.compareAndSet(atomicExample5,100,120)){
           log.info("update success 1,count:{}",atomicExample5.getCount());
       }
        if (updater.compareAndSet(atomicExample5,100,120)){
            log.info("update success 1,count:{}",atomicExample5.getCount());
        }else{
            log.info("update failed,{}",atomicExample5.getCount());
        }
    }
}
