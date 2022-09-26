package com.moxuanran.learning.cache.anno;

import com.moxuanran.learning.cache.ICacheLoader;

import java.lang.annotation.*;

/**
 * 构建缓存
 *
 * @author wutao
 * @date 2020/4/13 13:59
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CreateCache {

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
     * 是否缓存null值
     */
    boolean cacheNullValue() default false;

    /**
     * 缓存加载器
     */
    Class<? extends ICacheLoader> loader() default ICacheLoader.class;
}
