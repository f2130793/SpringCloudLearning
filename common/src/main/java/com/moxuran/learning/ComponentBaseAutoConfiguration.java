package com.moxuran.learning;

import com.moxuran.learning.config.BaseConfigBean;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author yjy
 * @date 2020/7/6 10:49
 **/
@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class ComponentBaseAutoConfiguration {

    @Bean
    @ConfigurationProperties("idanchuang.component.base")
    public BaseConfigBean baseConfigBean() {
        return new BaseConfigBean();
    }

}
