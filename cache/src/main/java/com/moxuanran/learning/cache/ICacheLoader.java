package com.moxuanran.learning.cache;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * 缓存加载器
 *
 * @author moxuanran 
 * 
 */
public interface ICacheLoader<K ,V> extends Function<K ,V> {

    V load(K key);

    default Map<K, V> loadAll(Set<K> keys) {
        Map<K, V> map = Maps.newHashMapWithExpectedSize(keys.size());
        keys.parallelStream().forEach(k -> {
            V value = load(k);
            if (value != null) {
                map.put(k, value);
            }
        });
        return map;
    }

    @Override
    default V apply(K key) {
        return load(key);
    }

}
