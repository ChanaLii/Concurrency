package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotions.NotThreadSafe;

/**
 * 【懒汉模式】：被调用时才实例化对象，避免资源浪费
 * @author ligy
 * @date 2018/4/7 0007 16:48
 */
@NotThreadSafe
public class SingletonExample1 {

    //私有构造方法
    private SingletonExample1() {

    }

    //单实例
    private static SingletonExample1 instance = null;

    //静态工厂方法
    public static SingletonExample1 getInstance(){
        //如果有2个线程同时操作null值判断，就会重复构造对象，导致2个线程最终得到的实例不是同一个
        if (instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
