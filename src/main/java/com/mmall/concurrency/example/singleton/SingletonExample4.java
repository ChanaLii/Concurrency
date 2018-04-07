package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotions.NotRecommand;
import com.mmall.concurrency.annotions.NotThreadSafe;
import com.mmall.concurrency.annotions.Recommand;
import com.mmall.concurrency.annotions.ThreadSafe;

/**
 * 双重同步锁
 * @author ligy
 * @date 2018/4/7 0007 16:48
 */
@NotThreadSafe("cpu与JVM优化介入，发生指令重排操作，高并发下可能导致线程获取了一个只分配了内存并未初始化的对象")
@NotRecommand
public class SingletonExample4 {

    //私有构造方法
    private SingletonExample4() {

    }

    //单实例
    private static SingletonExample4 instance = null;

    //1.memory = allocate()分配对象的内存空间
    //2.ctorInstance()初始化对象
    //3.instance = memory 设置instance指向刚分配的内存

    //静态工厂方法；double check机制；对于synchronized的使用建议：能用无锁数据结构，就不要用锁；能锁区块，就不要锁整个方法体；能用对象锁就不要类锁
    public static  SingletonExample4 getInstance(){
        if (instance == null){
            synchronized(SingletonExample4.class){
                if (instance == null){
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
