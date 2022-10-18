package com.moxuanran.learning.cache.support;

import com.moxuanran.learning.cache.ICacheLoader;
import com.moxuanran.learning.cache.anno.Cached;
import com.moxuanran.learning.cache.anno.CreateCache;
import com.moxuanran.learning.cache.config.PartialCacheConfig;
import com.moxuanran.learning.cache.context.SpringContextHolder;

/**
 * 缓存注解参数解析
 *
 * @author moxuanran 
 * 
 */
public class CacheAnnotationParser {

    public static <K, V> PartialCacheConfig<K, V> parseCached(Cached cached) {
        PartialCacheConfig<K, V> cacheConfig = new PartialCacheConfig<>();
        cacheConfig.setName(cached.name());
        cacheConfig.setExpireAfterWrite(cached.expire());
        cacheConfig.setLocalExpireAfterWrite(cached.localExpire());
        cacheConfig.setLocalCacheLimit(cached.localLimit());
        cacheConfig.setDefaultCacheLimit(cached.defaultLimit());
        cacheConfig.setCacheNullValue(cached.cacheNullValue());
        cacheConfig.setCacheType(cached.cacheType());
        return cacheConfig;
    }

    @SuppressWarnings("unchecked")
    public static <K, V> PartialCacheConfig<K, V> parseCreateCache(CreateCache createCache) {
        PartialCacheConfig<K, V> cacheConfig = new PartialCacheConfig<>();
        cacheConfig.setCacheType(createCache.cacheType());
        cacheConfig.setName(createCache.name());
        cacheConfig.setExpireAfterWrite(createCache.expire());
        cacheConfig.setLocalExpireAfterWrite(createCache.localExpire());
        cacheConfig.setLocalCacheLimit(createCache.localLimit());
        cacheConfig.setDefaultCacheLimit(createCache.defaultLimit());
        cacheConfig.setCacheNullValue(createCache.cacheNullValue());
        if (createCache.loader() != ICacheLoader.class) {
            cacheConfig.setCacheLoader(SpringContextHolder.getBean(createCache.loader()));
        }
        return cacheConfig;
    }

}
