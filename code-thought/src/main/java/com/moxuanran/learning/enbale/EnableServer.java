package com.moxuanran.learning.enbale;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/25 下午7:35
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(ServerImportSelector.class)
@Import(ServerImportBeanDefinitions.class)
public @interface EnableServer {
    /**
     * 类型
     *
     * @return {@link Server.Type}
     */
    Server.Type type();
}
