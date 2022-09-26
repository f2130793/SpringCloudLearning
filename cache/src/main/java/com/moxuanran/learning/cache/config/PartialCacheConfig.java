package com.moxuanran.learning.cache.config;

import com.moxuanran.learning.cache.ICacheLoader;
import com.moxuanran.learning.cache.anno.CacheType;
import lombok.Data;

/**
 * 局部缓存配置
 *
 * @author wutao
 * @date 2020/4/10 17:35
 */
@Data
public class PartialCacheConfig<K, V> {

    private String name;

    private int expireAfterWrite;

    private int localExpireAfterWrite;

    private int localCacheLimit;

    private int defaultCacheLimit;

    private boolean cacheNullValue;

    private ICacheLoader<K, V> cacheLoader;

    private CacheType cacheType;

}
