package com.moxuanran.learning.job;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * @author 莫轩然
 * @date 2020/10/27 13:58
 */
@Component
public class MyJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        switch (shardingContext.getShardingItem()){
            case 0:
                System.out.println(0);
                break;
            case 1:
                System.out.println(111);
                break;
            case 2:
                System.out.println(222);
                break;
            default:
                System.out.println(999);
                break;
        }
    }
}
