package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotions.Recommand;
import com.mmall.concurrency.annotions.ThreadSafe;

/**
 * 枚举模式：最安全
 * @author ligy
 * @date 2018/4/7 0007 16:48
 */
@ThreadSafe
@Recommand
public class SingletonExample7 {

    //私有构造方法
    private SingletonExample7() {

    }
    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        //JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singleton;
        }
    }

}
