package com.moxuanran.learning.cache;

import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * cache管理器
 *
 * @author wutao
 * @date 2020/4/13 9:59
 */
public class CacheManager {

    private static ConcurrentMap<String, ICache<Object, Object>> caches = Maps.newConcurrentMap();

    @SuppressWarnings("unchecked")
    public static <K, V> ICache<K, V> getCache(String name) {
        return (ICache<K, V>) caches.get(name);
    }

    public static ICache<Object, Object> computeIfAbsent(String name, Function<? super String, ? extends ICache<Object, Object>> mappingFunction) {
        return caches.computeIfAbsent(name, mappingFunction);
    }

}
