package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotions.NotRecommand;
import com.mmall.concurrency.annotions.NotThreadSafe;
import com.mmall.concurrency.annotions.ThreadSafe;

/**
 * 【饿汉模式】：类加载时就实例化单例对象
 * 使用此模式需满足：1.系统一定会调用此对象 2.构造方法没有过多的处理，否则会导致类加载慢
 * @author ligy
 * @date 2018/4/7 0007 16:48
 */
@ThreadSafe
@NotRecommand
public class SingletonExample2 {

    //私有构造方法，没有过多的处理
    private SingletonExample2() {

    }

    //单实例
    private static SingletonExample2 instance = new SingletonExample2();

    //静态工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
