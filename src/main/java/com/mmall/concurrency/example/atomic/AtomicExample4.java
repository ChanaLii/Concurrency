package com.mmall.concurrency.example.atomic;

import com.mmall.concurrency.annotions.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ligy
 * @date 2018/4/7 0007 10:43
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {
    private static AtomicReference<Integer> count = new AtomicReference<>(0);
    public static void main(String[] args){
        count.compareAndSet(0,2);
        count.compareAndSet(0,1);
        count.compareAndSet(1,3);
        count.compareAndSet(2,4);
        count.compareAndSet(3,5);
        log.info("count:{}",count.get());
    }
}
