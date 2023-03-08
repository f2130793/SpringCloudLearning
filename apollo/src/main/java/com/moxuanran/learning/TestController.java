package com.moxuanran.learning;

import com.netease.bchain.component.apollo.util.ConfigUtil;
import com.netease.bchain.spi.config.IConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wutao
 * @date 2023/1/3 16:06
 */
@RestController
public class TestController {
    @Autowired
    private RefreshConfig refreshConfig;

    @Autowired
    private CommonConfig commonConfig;

    @Autowired
    private IConfig config;

    @GetMapping("test")
    public String test() {
        return refreshConfig.getName() + ":" + refreshConfig.getTimeout() + "/n"
                + commonConfig.getName() + ":" + commonConfig.getDesc();
    }

    @GetMapping("config")
    public String config() {
        return ConfigUtil.getConfig().getKeys().toString();
//        return config.getKeys().toString();
    }
}
