package com.mmall.concurrency.annotions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 【不推荐】写法
 * @author ligy
 * @date 2018/4/6 0006 21:55
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotRecommand {
    String value() default "";
}
