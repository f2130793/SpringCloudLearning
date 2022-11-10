package com.moxuanran.star.demo;

import com.netease.bchain.starrpc.StarRpc;
import com.netease.bchain.starrpc.integration.spring.StarRpcSpringClientRegister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wutao
 * @date 2022/11/10 09:46
 */
@Configuration
public class Config {
    @Bean
    public StarRpcSpringClientRegister starRpcSpringClientRegister() {
        return StarRpc.integrateClientWithSpring("com.netease");
    }
}
