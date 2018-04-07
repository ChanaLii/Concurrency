package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotions.ThreadSafe;

/**
 * 静态块
 * @author ligy
 * @date 2018/4/7 0007 16:48
 */
@ThreadSafe("使用静态块方法实例化对象需要注意static代码块和对象的先后次序，防止null对象覆盖了被实例化的对象造成最终得到的是null对象")
public class SingletonExample6 {

    //私有构造方法
    private SingletonExample6() {

    }

    //单实例
    private  static SingletonExample6 instance = null;

    static{
        instance = new SingletonExample6();
    }

    public static SingletonExample6 getInstance(){
        return instance;
    }
}
