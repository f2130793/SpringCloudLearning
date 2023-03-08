package com.moxuanran.learning;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wutao
 * @date 2023/1/4 16:41
 */
@Configuration
@ConfigurationProperties(prefix = "common")
@Data
public class CommonConfig {
    private String name;
    private String desc;
}
