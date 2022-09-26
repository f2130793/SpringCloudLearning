package com.moxuanran.learning.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Maps;
import com.moxuanran.learning.cache.config.PartialCacheConfig;
import com.moxuanran.learning.cache.support.CacheLoaderWrapper;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * 关闭缓存时使用
 *
 * @author wutao
 * @date 2020/4/13 20:31
 */
public class DisableCache<K, V> implements ICache<K, V> {

    private Cache<K, V> defaultCache;

    private PartialCacheConfig<K, V> partialCacheConfig;

    @SuppressWarnings("unchecked")
    public DisableCache(ICacheLoader<?, ?> cacheLoader) {
        partialCacheConfig = new PartialCacheConfig<>();
        partialCacheConfig.setName("null");
        partialCacheConfig.setExpireAfterWrite(0);
        partialCacheConfig.setLocalCacheLimit(0);
        partialCacheConfig.setLocalExpireAfterWrite(0);
        partialCacheConfig.setCacheLoader((ICacheLoader<K, V>) cacheLoader);
        defaultCache = Caffeine.newBuilder().maximumSize(partialCacheConfig.getDefaultCacheLimit()).build();
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public Map<K, V> getAll(Set<? extends K> keys) {
        return Maps.newHashMap();
    }

    @Override
    public void put(K key, V value, long expireAfterWrite) {

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map, long expireAfterWrite) {

    }

    @Override
    public void remove(K key) {

    }

    @Override
    public void removeAll(Set<? extends K> keys) {

    }

    @Override
    public V computeIfAbsent(K key, Function<K, V> loader, long expireAfterWrite) {
        return CacheLoaderWrapper.wrap(loader).apply(key);
    }

    @Override
    public PartialCacheConfig<K, V> getPartialCacheConfig() {
        return partialCacheConfig;
    }

    @Override
    public Cache<K, V> getDefaultCache() {
        return defaultCache;
    }

}
