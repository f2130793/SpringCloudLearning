package com.moxuanran.learning.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author wutao
 * @date 2022/9/29 16:55
 */
@Service
public class DemoTask extends AbstractTimedTask{
    protected DemoTask(StringRedisTemplate redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public long getInterval() {
        return 3;
    }

    @Override
    public void runOnce() {
        System.out.println("run demo task");
    }
}
