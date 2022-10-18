package com.moxuanran.learning.cache.anno;


import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.*;

/**
 * 方法级缓存
 *
 * @author moxuanran 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cached {

    /**
     * 缓存名称
     */
    String name();

    /**
     * 缓存过期时间
     */
    int expire() default 0;

    /**
     * 本地缓存过期时间
     */
    int localExpire() default 0;

    /**
     * 缓存类型
     */
    CacheType cacheType() default CacheType.REMOTE;

    /**
     * 本地缓存大小
     */
    int localLimit() default 0;

    /**
     * 默认缓存大小
     */
    int defaultLimit() default 0;

    /**
     * 缓存key SpEL表达式
     */
    String key() default StringUtils.EMPTY;

    /**
     * 是否缓存null值
     */
    boolean cacheNullValue() default false;

}
