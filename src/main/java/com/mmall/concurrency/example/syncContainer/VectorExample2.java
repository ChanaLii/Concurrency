package com.mmall.concurrency.example.syncContainer;

import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author ligy
 * @date 2018/4/9 0009 22:15
 */
@Slf4j
public class VectorExample2 {


    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        while (true){
            for (int i = 0; i < 10 ; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread(){
                @Override
                public void run(){
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread(){
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}
