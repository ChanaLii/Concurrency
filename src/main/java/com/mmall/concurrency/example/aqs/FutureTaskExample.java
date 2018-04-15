package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ligy
 * @date 2018/4/15 0015 23:06
 */
@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            //线程执行结果回调方法
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });

        new Thread(futureTask).start();
        log.info("do something in main func");
        Thread.sleep(1000);
        String result = futureTask.get();
        log.info("result:{}",result);
    }
}
