package com.mmall.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.mmall.concurrency.annotions.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * 不可变对象
 * @author ligy
 * @date 2018/4/7 0007 20:45
 */
@Slf4j
@ThreadSafe("使用Google的工具类实现")
public class ImmutableExample3 {

   private static final ImmutableList list = ImmutableList.of(1,2,3,4,5,6,6);

   private static final ImmutableSet set = ImmutableSet.copyOf(list);

   private static final ImmutableMap map = ImmutableMap.of(1,2,3,4,5,6,7,8);

   private static final ImmutableMap map1 = ImmutableMap.builder().put(1,2).put(3,4).put(5,6).put(7,8).build();

    public static void main(String[] args) {
        /*list.add(1);
        set.add(1);
        map.put(1,3);*/
        log.info("list:{}",list);
        log.info("set:{}",set);
        log.info("map:{}",map);
        log.info("map1:{}",map1);
    }

}
