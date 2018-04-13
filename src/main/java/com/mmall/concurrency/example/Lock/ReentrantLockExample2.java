package com.mmall.concurrency.example.Lock;

import javafx.scene.chart.PieChart;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ligy
 * @date 2018/4/12 0012 23:41
 */
@Slf4j
public class ReentrantLockExample2 {

    private static Map<String ,Object> map = new TreeMap<>();

    private static ReentrantReadWriteLock lock  = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Object getString(String key){
        readLock.lock();
        try{
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys(){
        readLock.lock();
        try{
            return map.keySet();
        }finally {
            readLock.unlock();
        }
    }

    public Object put(String key,Data value){
        writeLock.lock();
        try{
            return map.put(key,value);
        }finally {
            readLock.unlock();
        }
    }
}
