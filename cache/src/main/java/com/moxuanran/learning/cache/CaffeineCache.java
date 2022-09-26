package com.moxuanran.learning.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.moxuanran.learning.cache.config.PartialCacheConfig;
import com.moxuanran.learning.cache.support.CacheValueHolder;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Caffeine JVM缓存
 *
 * @author wutao
 * @date 2020/4/10 18:16
 */
public class CaffeineCache<K, V> extends AbstractCache<K, V> {

    private Cache<K, CacheValueHolder<V>> cache;

    public CaffeineCache(PartialCacheConfig<K, V> cacheConfig) {
        super(cacheConfig);
        cache = Caffeine.newBuilder()
                .maximumSize(cacheConfig.getLocalCacheLimit())
                .expireAfter(new Expiry<K, CacheValueHolder<V>>() {
                    @Override
                    public long expireAfterCreate(@Nonnull K key, @Nonnull CacheValueHolder<V> value, long currentTime) {
                        return TimeUnit.NANOSECONDS.convert(value.getExpireAfterWrite(), TimeUnit.MILLISECONDS);
                    }
                    @Override
                    public long expireAfterUpdate(@Nonnull K key, @Nonnull CacheValueHolder<V> value, long currentTime, long currentDuration) {
                        return currentDuration;
                    }
                    @Override
                    public long expireAfterRead(@Nonnull K key, @Nonnull CacheValueHolder<V> value, long currentTime, long currentDuration) {
                        return currentDuration;
                    }
                })
                .build();
    }

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        CacheValueHolder<V> valueHolder = cache.getIfPresent(key);
        return valueHolder != null ? valueHolder.getValue() : null;
    }

    @Override
    public Map<K, V> getAll(Set<? extends K> keys) {
        return cache.getAllPresent(keys).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getValue()));
    }

    @Override
    public void put(K key, V value, long expireAfterWrite) {
        cache.put(key, new CacheValueHolder<>(value, expireAfterWrite));
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map, long expireAfterWrite) {
        cache.putAll(map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> new CacheValueHolder<>(entry.getValue(), expireAfterWrite))));
    }

    @Override
    public void remove(K key) {
        cache.invalidate(key);
    }

    @Override
    public void removeAll(Set<? extends K> keys) {
        cache.invalidateAll(keys);
    }

}
