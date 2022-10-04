package com.moxuanran.learning.redis;

import java.util.concurrent.TimeUnit;

/**
 * @author wutao
 * @date 2022/9/29 16:20
 */
public interface TimedTask {
    /**
     * 开始任务
     */
    void start();

    /**
     * 任务名称，唯一
     *
     * @return {@link String}
     */
    String getTaskName();

    /**
     * 让时间单位
     *
     * @return {@link TimeUnit}
     */
    TimeUnit getTimeUnit();

    /**
     * 首次执行延迟
     *
     * @return long
     */
    long getInitialDelay();

    /**
     * 执行周期
     *
     * @return long
     */
    long getInterval();

    /**
     * 执行
     */
    void runOnce();

    /**
     * 销毁
     */
    void destroy();
}
