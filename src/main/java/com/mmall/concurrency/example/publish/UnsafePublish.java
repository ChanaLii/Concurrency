package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annotions.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 多线程并发情况下，states数组可能会被其他线程进行修改，导致数组的状态变更
 * @author ligy
 * @date 2018/4/7 0007 11:49
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a","b","c"};
    //非安全发布对象
    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}",Arrays.toString(unsafePublish.getStates()));
    }
}
