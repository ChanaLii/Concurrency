package com.mmall.concurrency.example.syncContainer;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author ligy
 * @date 2018/4/9 0009 22:37
 */
@Slf4j
public class CollectionExample1 {
    private static final int CLIENT_TOTAL  = 5000;

    private static final int THREAD_TOTAL = 200;

    private static List<Integer> list = Collections.synchronizedList(Lists.newArrayList());

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(THREAD_TOTAL);
        final CountDownLatch countDownLatch = new CountDownLatch(CLIENT_TOTAL);
        for (int i = 0; i < CLIENT_TOTAL; i++) {
            final int count = i;
            executorService.execute(()->{
                try {
                    //获取信号量许可证
                    semaphore.acquire();
                    update(count);
                    //释放信号量许可证
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //释放线程
                countDownLatch.countDown();
            });
        }
        //休眠
        countDownLatch.await();
        //关闭线程
        executorService.shutdown();
        log.info("{}",list.size());
    }

    private static void update(int i){
        list.add(1);
    }
}
