package com.moxuanran.learning.redis;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wutao
 * @date 2022/9/29 16:47
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisTask {
    /** 任务名称 */
    String value() default "";

    /**
     * @return 执行周期
     */
    long interval();

    /** 推迟开始 */
    long initialDelay() default  0L;

    /**
     * @return 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
