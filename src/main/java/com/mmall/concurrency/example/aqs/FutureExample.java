package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ThrowsAdvice;

import java.util.concurrent.*;

/**
 * 如果业务场景需要得知线程是否正常执行，以及执行结果可以使用Future
 * Future获取线程执行结果
 * @author ligy
 * @date 2018/4/15 0015 22:57
 */
@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            //线程阻塞5秒
            Thread.sleep(5000);
            return "DONE";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        log.info("do something in main");
        Thread.sleep(1000);
        String result = future.get();
        log.info("result:{}",result);
    }

}
