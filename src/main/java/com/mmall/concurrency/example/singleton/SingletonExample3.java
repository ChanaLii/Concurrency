package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotions.NotRecommand;
import com.mmall.concurrency.annotions.ThreadSafe;

/**
 * 加方法锁
 * @author ligy
 * @date 2018/4/7 0007 16:48
 */
@ThreadSafe
@NotRecommand
public class SingletonExample3 {

    //私有构造方法
    private SingletonExample3() {

    }

    //单实例
    private static SingletonExample3 instance = null;

    //静态工厂方法,锁静态方法会造成另外资源的开销
    public static synchronized SingletonExample3 getInstance(){
        //线程安全但会出现持锁时间过长
        if (instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
