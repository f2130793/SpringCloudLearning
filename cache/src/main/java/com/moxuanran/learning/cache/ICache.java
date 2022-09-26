package com.moxuanran.learning.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.moxuanran.learning.cache.config.GlobalCacheConfig;
import com.moxuanran.learning.cache.config.PartialCacheConfig;
import com.moxuanran.learning.cache.support.KeyConverter;
import com.moxuanran.learning.cache.support.StringKeyCombiner;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 缓存接口
 *
 * @author wutao
 * @date 2020/4/10 15:49
 */
public interface ICache<K, V> {

    /**
     * Gets an entry from the cache.
     * <p>If error occurs during cache access, the method return null instead of throwing an exception.</p>
     *
     * @param key the key whose associated value is to be returned
     * @return the associated value. null may indicates: <ul>
     * <li>the entry does not exist or expired</li>
     * <li>the entry value is null</li>
     * <li>error occurs during cache access(no exception throws)</li>
     * </ul>
     */
    V get(K key);

    /**
     * Gets a collection of entries from the Cache, returning them as Map of the values associated with
     * the set of keys requested.
     * <p>If error occurs during cache access, the method will not throw an exception.</p>
     *
     * @param keys The keys whose associated values are to be returned.
     * @return A map of entries that were found for the given keys. Keys not found in the cache are not in the returned map.
     */
    Map<K, V> getAll(Set<? extends K> keys);

    /**
     * Associates the specified value with the specified key in the cache.
     * <p>If error occurs during cache access, the method will not throw an exception.</p>
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    default void put(K key, V value) {
        put(key, value, getExpireAfterWrite());
    }

    /**
     * Associates the specified value with the specified key in the cache.
     * <p>If error occurs during cache access, the method will not throw an exception.</p>
     *
     * @param key              key with which the specified value is to be associated
     * @param value            value to be associated with the specified key
     * @param expireAfterWrite the TTL(time to live) of the KV association
     */
    void put(K key, V value, long expireAfterWrite);

    /**
     * Copies all the entries from the specified map to the cache.
     * <p>If error occurs during cache access, the method will not throw an exception.</p>
     *
     * @param map mappings to be stored in this cache.
     */
    default void putAll(Map<? extends K, ? extends V> map) {
        putAll(map, getExpireAfterWrite());
    }

    /**
     * Copies all the entries from the specified map to the cache.
     * <p>If error occurs during cache access, the method will not throw an exception.</p>
     *
     * @param map              mappings to be stored in this cache.
     * @param expireAfterWrite the TTL(time to live) of the KV association
     */
    void putAll(Map<? extends K, ? extends V> map, long expireAfterWrite);

    /**
     * Removes the mapping for a key from this cache if it is present.
     * <p>If error occurs during cache access, the method will not throw an exception.</p>
     *
     * @param key key whose mapping is to be removed from the cache
     * @return true if the key is removed successfully, false if the KV association does not exists in the cache,
     * or error occurs during cache access.
     */
    void remove(K key);

    /**
     * Removes entries for the specified keys.
     * <p>If error occurs during cache access, the method will not throw an exception.</p>
     *
     * @param keys the keys to remove
     */
    void removeAll(Set<? extends K> keys);

    /**
     * If there is a value associated with the key, return the value;
     * otherwise use the loader load the value and return, and then update the cache.
     *
     * @param key the key
     * @return the value
     */
    default V computeIfAbsent(K key) {
        ICacheLoader<K, V> cacheLoader = getPartialCacheConfig().getCacheLoader();
        if (cacheLoader == null) {
            throw new UnsupportedOperationException("cacheLoader can't be null");
        }
        return computeIfAbsent(key, cacheLoader, getExpireAfterWrite());
    }

    /**
     * If there is a value associated with the key, return the value;
     * otherwise use the loader load the value and return, and then update the cache.
     *
     * @param key the key
     * @param expireAfterWrite the TTL(time to live) of the KV association
     * @return the value
     */
    default V computeIfAbsent(K key, long expireAfterWrite) {
        ICacheLoader<K, V> cacheLoader = getPartialCacheConfig().getCacheLoader();
        if (cacheLoader == null) {
            throw new UnsupportedOperationException("cacheLoader can't be null");
        }
        return computeIfAbsent(key, cacheLoader, expireAfterWrite);
    }

    /**
     * If there is a value associated with the key, return the value;
     * otherwise use the loader load the value and return, and then update the cache.
     *
     * @param key    the key
     * @param loader the value loader
     * @return the value
     */
    default V computeIfAbsent(K key, Function<K, V> loader) {
        return computeIfAbsent(key, loader, getExpireAfterWrite());
    }

    /**
     * If there is a value associated with the key, return the value;
     * otherwise use the loader load the value and return, and then update the cache.
     *
     * @param key              the key
     * @param loader           the value loader
     * @param expireAfterWrite the TTL(time to live) of the KV association
     * @return the value
     */
    V computeIfAbsent(K key, Function<K, V> loader, long expireAfterWrite);

    /**
     * 重新加载缓存
     *
     * @param key 缓存key
     */
    default void reload(K key) {
        ICacheLoader<K, V> cacheLoader = getPartialCacheConfig().getCacheLoader();
        if (cacheLoader == null) {
            throw new UnsupportedOperationException("cacheLoader can't be null");
        }
        put(key, cacheLoader.load(key));;
    }

    /**
     * 重新加载缓存
     *
     * @param keys 缓存key
     */
    default void reloadAll(Set<K> keys) {
        ICacheLoader<K, V> cacheLoader = getPartialCacheConfig().getCacheLoader();
        if (cacheLoader == null) {
            throw new UnsupportedOperationException("cacheLoader can't be null");
        }
        putAll(cacheLoader.loadAll(keys));
    }

    /**
     * 获取缓存超时时间
     *
     * @return 缓存超时时间
     */
    default long getExpireAfterWrite() {
        PartialCacheConfig<K, V> partialCacheConfig = getPartialCacheConfig();
        return partialCacheConfig.getExpireAfterWrite() + ThreadLocalRandom.current().nextLong(GlobalCacheConfig.getExpireRandom());
    }

    /**
     * 获取缓存局部配置
     *
     * @return 缓存局部配置
     */
    PartialCacheConfig<K, V> getPartialCacheConfig();

    /**
     * 构建缓存key
     *
     * @param key 缓存key
     * @return 序列化后的缓存key
     */
    default String buildKey(K key) {
        return StringKeyCombiner.combine(GlobalCacheConfig.getKeyPrefix(), getPartialCacheConfig().getName(), KeyConverter.convert(key));
    }

    /**
     * 构建缓存key
     *
     * @param keys 缓存key
     * @return 序列化后的缓存key
     */
    default List<String> buildKeys(Collection<? extends K> keys) {
        return keys.stream().map(this::buildKey).collect(Collectors.toList());
    }

    /**
     * 构建缓存锁key
     *
     * @param key 缓存key
     * @return 序列化后的缓存锁key
     */
    default String buildLockKey(K key) {
        return StringKeyCombiner.combine(GlobalCacheConfig.getKeyPrefix(), "lock", getPartialCacheConfig().getName(), KeyConverter.convert(key));
    }

    /**
     * 是否有默认缓存
     *
     * @return 是否有默认缓存
     */
    default boolean hasDefaultCache() {
        return getDefaultCache() != null;
    }

    /**
     * 获取默认缓存
     *
     * @return 默认缓存
     */
    Cache<K, V> getDefaultCache();

    /**
     * 从默认缓存中获取任意值
     *
     * @return 任意值
     */
    default V getAnyFromDefaultCache() {
        if (hasDefaultCache()) {
            return getDefaultCache().asMap().values().stream().findAny().orElse(null);
        }
        return null;
    }

    /**
     * 缓存降级处理
     *
     * @param expireAfterWrite 原缓存超时时间
     * @return 如果缓存降级，则返回全局缓存降级超时时间
     */
    default long expireDowngrade(long expireAfterWrite) {
        return CacheContext.getCacheDowngradeList().contains(getPartialCacheConfig().getName())
                ? GlobalCacheConfig.getDowngradeExpireAfterWrite() : expireAfterWrite;
    }

    /**
     * 获取剩余超时时间
     * @param key 缓存key
     * @return 剩余超时时间
     */
    default Long getExpire(K key) {
        return null;
    }

}
