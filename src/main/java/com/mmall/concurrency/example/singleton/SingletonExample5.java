package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotions.NotRecommand;
import com.mmall.concurrency.annotions.NotThreadSafe;
import com.mmall.concurrency.annotions.ThreadSafe;

/**
 * 懒汉模式 -> 双重同步锁单例模式
 * @author ligy
 * @date 2018/4/7 0007 16:48
 */
@ThreadSafe("使用volatie + double check禁止cpu与JVM的指令重排操作")
public class SingletonExample5 {

    //私有构造方法
    private SingletonExample5() {

    }

    //volatile确保对象被初始化；单实例
    private volatile static SingletonExample5 instance = null;

    //1.memory = allocate()分配对象的内存空间
    //2.ctorInstance()初始化对象
    //3.instance = memory 设置instance指向刚分配的内存

    //cpu与JVM优化，介入指令重排操作

    //1.memory = allocate()分配对象的内存空间
    //3.instance = memory 设置instance指向刚分配的内存
    //2.ctorInstance()初始化对象

    //静态工厂方法；double check机制；对于synchronized的使用建议：能用无锁数据结构，就不要用锁；能锁区块，就不要锁整个方法体；能用对象锁就不要类锁
    public static SingletonExample5 getInstance(){
        //是否分配内存
        if (instance == null){
            synchronized(SingletonExample5.class){
                if (instance == null){
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
