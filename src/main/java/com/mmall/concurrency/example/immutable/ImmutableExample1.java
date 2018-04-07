package com.mmall.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.mmall.concurrency.annotions.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ligy
 * @date 2018/4/7 0007 20:45
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private static final int a  = 1;
    private static final String b ="2";
    private static final Map<Integer,Integer> map = Maps.newHashMap();

    static{
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
    }

    public static void main(String[] args) {
        /* final类型的基本变量不允许更改，
        a = 1;
        b = 2;
        */
        //final类型的对象不允许再次初始化，但允许修改
        //map = new HashMap<>();
        map.put(1,100);
        map.put(2,200);
        map.put(3,300);
        int aResult = test(a);
        log.info("map:{}",map);
        log.info("a:{}",a);
        log.info("aResult:{}",aResult);
    }

    public static int test(int a){
        a = 10;
        return a;
    }
}
