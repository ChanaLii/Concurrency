package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annotions.NotRecommand;
import com.mmall.concurrency.annotions.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ligy
 * @date 2018/4/7 0007 16:42
 */
@Slf4j
@NotThreadSafe("未完成构造参数就被发布")
@NotRecommand
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape(){
        new InnerClass();
    }

    private class InnerClass{
        public InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
