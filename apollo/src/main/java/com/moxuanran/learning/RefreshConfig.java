package com.moxuanran.learning;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wutao
 * @date 2023/1/4 16:02
 */
@Configuration
@ConfigurationProperties(prefix = "refresh")
@Data
public class RefreshConfig {
    private String name;
    private int timeout;
}
