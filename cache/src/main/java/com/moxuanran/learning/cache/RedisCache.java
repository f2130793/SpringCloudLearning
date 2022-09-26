package com.moxuanran.learning.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.moxuanran.learning.cache.config.PartialCacheConfig;
import com.moxuanran.learning.cache.context.SpringContextHolder;
import com.moxuanran.learning.cache.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存
 *
 * @author wutao
 * @date 2020/4/10 15:47
 */
@Slf4j
public class RedisCache<K, V> extends AbstractCache<K, V> {

    private StringRedisTemplate stringRedisTemplate;

    public RedisCache(PartialCacheConfig<K, V> cacheConfig) {
        super(cacheConfig);
        stringRedisTemplate = SpringContextHolder.getBean("stringRedisTemplate");
    }

    @Override
    public V get(K key) {
        String value = stringRedisTemplate.opsForValue().get(buildKey(key));
        return value != null ? JSON.parseObject(value, Object.class, JSONUtil.PARSER_CONFIG) : null;
    }

    @Override
    public Map<K, V> getAll(Set<? extends K> keys) {
        Map<K, V> result = Maps.newHashMapWithExpectedSize(keys.size());
        List<K> keyList = Lists.newArrayList(keys);
        List<String> valueList = stringRedisTemplate.opsForValue().multiGet(buildKeys(keyList));
        if (valueList == null || valueList.size() == 0) {
            return result;
        }
        for (int i = 0; i < valueList.size(); i++) {
            if (valueList.get(i) != null) {
                result.put(keyList.get(i), JSON.parseObject(valueList.get(i), Object.class, JSONUtil.PARSER_CONFIG));
            }
        }
        return result;
    }

    @Override
    public void put(K key, V value, long expireAfterWrite) {
        stringRedisTemplate.opsForValue().set(
                buildKey(key),
                JSON.toJSONString(value, SerializerFeature.WriteClassName),
                expireDowngrade(expireAfterWrite), TimeUnit.MILLISECONDS);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map, long expireAfterWrite) {
        map.forEach((key, value) -> put(key, value, expireDowngrade(expireAfterWrite)));
    }

    @Override
    public void remove(K key) {
        stringRedisTemplate.delete(buildKey(key));
    }

    @Override
    public void removeAll(Set<? extends K> keys) {
        stringRedisTemplate.delete(buildKeys(keys));
    }

    @Override
    public Long getExpire(K key) {
        return stringRedisTemplate.getExpire(buildKey(key), TimeUnit.MILLISECONDS);
    }

}
