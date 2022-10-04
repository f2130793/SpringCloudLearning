package com.moxuanran.learning.formatterspringbootstarter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/26 21:26
 */
@Configuration
public class FormatterAutoConfiguration {
    @Bean
    public Formatter defaultFormatter() {
        return new DefaultFormatter();
    }
}
