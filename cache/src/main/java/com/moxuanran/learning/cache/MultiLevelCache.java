package com.moxuanran.learning.cache;


import com.moxuanran.learning.cache.config.PartialCacheConfig;
import com.moxuanran.learning.cache.config.SysConfig;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 多级缓存
 *
 * @author moxuanran 
 * 
 */
public class MultiLevelCache<K, V> extends AbstractCache<K, V> {

    private ICache<K, V> localCache;

    private ICache<K, V> remoteCache;

    public MultiLevelCache(ICache<K, V> localCache, ICache<K, V> remoteCache, PartialCacheConfig<K, V> cacheConfig) {
        super(cacheConfig);
        this.localCache = localCache;
        this.remoteCache = remoteCache;
    }

    @Override
    public V get(K key) {
        V value;
        if (SysConfig.getInstance().getSwitchUseMultiLevelCache()) {
            value = localCache.get(key);
            if (value == null) {
                value = remoteCache.get(key);
                if (value != null) {
                    Long leftExpire = remoteCache.getExpire(key);
                    if (leftExpire != -2) {
                        int localExpireAfterWrite = localCache.getPartialCacheConfig().getLocalExpireAfterWrite();
                        localCache.put(key, value, leftExpire != -1 && localExpireAfterWrite > leftExpire ? leftExpire : localExpireAfterWrite);
                    }
                }
            }
        } else {
            value = remoteCache.get(key);
        }
        return value;
    }

    @Override
    public Map<K, V> getAll(Set<? extends K> keys) {
        if (SysConfig.getInstance().getSwitchUseMultiLevelCache()) {
            final Map<K, V> cacheMap = localCache.getAll(keys);
            if (keys.size() == cacheMap.size()) {
                return cacheMap;
            }
            Set<? extends K> localCacheNonexistentKeys
                    = keys.stream().filter(key -> !cacheMap.containsKey(key)).collect(Collectors.toSet());
            Map<K, V> remoteCacheMap = remoteCache.getAll(localCacheNonexistentKeys);
            cacheMap.putAll(remoteCacheMap);
            for (Map.Entry<K, V> entry : remoteCacheMap.entrySet()) {
                Long leftExpire = remoteCache.getExpire(entry.getKey());
                if (leftExpire == -2) {
                    continue;
                }
                int localExpireAfterWrite = localCache.getPartialCacheConfig().getLocalExpireAfterWrite();
                localCache.put(entry.getKey(), entry.getValue(), leftExpire != -1 && localExpireAfterWrite > leftExpire ? leftExpire : localExpireAfterWrite);
            }
            localCache.putAll(remoteCacheMap);
            return cacheMap;
        } else {
            return remoteCache.getAll(keys);
        }
    }

    @Override
    public void put(K key, V value, long expireAfterWrite) {
        if (SysConfig.getInstance().getSwitchUseMultiLevelCache()) {
            localCache.put(key, value);
        }
        remoteCache.put(key, value, expireDowngrade(expireAfterWrite));
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map, long expireAfterWrite) {
        if (SysConfig.getInstance().getSwitchUseMultiLevelCache()) {
            localCache.putAll(map);
        }
        remoteCache.putAll(map, expireDowngrade(expireAfterWrite));
    }

    @Override
    public void remove(K key) {
        if (SysConfig.getInstance().getSwitchUseMultiLevelCache()) {
            localCache.remove(key);
        }
        remoteCache.remove(key);
    }

    @Override
    public void removeAll(Set<? extends K> keys) {
        if (SysConfig.getInstance().getSwitchUseMultiLevelCache()) {
            localCache.removeAll(keys);
        }
        remoteCache.removeAll(keys);
    }

}
