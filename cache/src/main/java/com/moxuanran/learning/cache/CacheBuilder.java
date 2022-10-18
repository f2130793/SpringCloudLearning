package com.moxuanran.learning.cache;

import com.moxuanran.learning.cache.anno.CacheType;
import com.moxuanran.learning.cache.aspect.CacheInvocationHandler;
import com.moxuanran.learning.cache.config.GlobalCacheConfig;
import com.moxuanran.learning.cache.config.PartialCacheConfig;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * 缓存构建
 *
 * @author moxuanran 
 * 
 */
@Accessors(chain = true)
@Data
public class CacheBuilder {

    private String name;

    private int expireAfterWrite;

    private int localExpireAfterWrite;

    private int localCacheLimit;

    private CacheType cacheType;

    private int defaultCacheLimit;

    private ICacheLoader<?, ?> cacheLoader;

    public static CacheBuilder createCacheBuilder() {
        return new CacheBuilder();
    }

    @SuppressWarnings("unchecked")
    public <K, V> ICache<K, V> build() {
        return (ICache<K, V>) CacheManager.computeIfAbsent(name, key -> {
            Objects.requireNonNull(cacheType, "cacheType can't be null");
            ICache<K, V> cache;
            switch (cacheType) {
                case LOCAL:
                    cache = createLocalCache();
                    break;
                case REMOTE:
                    cache = createRemoteCache();
                    break;
                case BOTH:
                    cache = createMultiLevelCache();
                    break;
                default:
                    throw new UnsupportedOperationException("cacheType is unsupported");
            }
            return (ICache<Object, Object>) Proxy.newProxyInstance(cache.getClass().getClassLoader(),
                    new Class[]{ICache.class}, new CacheInvocationHandler((ICache<Object, Object>) cache));
        });
    }

    @SuppressWarnings("unchecked")
    private <K, V> ICache<K, V> createLocalCache() {
        PartialCacheConfig<K, V> cacheConfig = new PartialCacheConfig<>();
        cacheConfig.setName(name);
        cacheConfig.setLocalCacheLimit(localCacheLimit != 0 ? localCacheLimit : GlobalCacheConfig.getLocalCacheLimit());
        cacheConfig.setLocalExpireAfterWrite(localExpireAfterWrite != 0 ? localExpireAfterWrite : GlobalCacheConfig.getLocalExpireAfterWrite());
        cacheConfig.setExpireAfterWrite(cacheConfig.getLocalExpireAfterWrite());
        cacheConfig.setDefaultCacheLimit(defaultCacheLimit);
        cacheConfig.setCacheLoader((ICacheLoader<K, V>) cacheLoader);
        return new CaffeineCache<>(cacheConfig);
    }

    @SuppressWarnings("unchecked")
    private <K, V> ICache<K, V> createRemoteCache() {
        PartialCacheConfig<K, V> cacheConfig = new PartialCacheConfig<>();
        cacheConfig.setExpireAfterWrite(expireAfterWrite != 0 ? expireAfterWrite : GlobalCacheConfig.getExpireAfterWrite());
        cacheConfig.setName(name);
        cacheConfig.setDefaultCacheLimit(defaultCacheLimit);
        cacheConfig.setCacheLoader((ICacheLoader<K, V>) cacheLoader);
        return new RedisCache<>(cacheConfig);
    }

    @SuppressWarnings("unchecked")
    private <K, V> ICache<K, V> createMultiLevelCache() {
        PartialCacheConfig<K, V> cacheConfig = new PartialCacheConfig<>();
        cacheConfig.setName(name);
        cacheConfig.setExpireAfterWrite(expireAfterWrite != 0 ? expireAfterWrite : GlobalCacheConfig.getExpireAfterWrite());
        cacheConfig.setLocalExpireAfterWrite(localExpireAfterWrite != 0 ? localExpireAfterWrite : GlobalCacheConfig.getLocalExpireAfterWrite());
        cacheConfig.setCacheLoader((ICacheLoader<K, V>) cacheLoader);
        cacheConfig.setDefaultCacheLimit(defaultCacheLimit);
        cacheConfig.setLocalCacheLimit(localCacheLimit != 0 ? localCacheLimit : GlobalCacheConfig.getLocalCacheLimit());
       return new MultiLevelCache<>(createLocalCache(), createRemoteCache(), cacheConfig);
    }

}
