package com.moxuanran.learning.cache.support;

import lombok.Data;

import java.io.Serializable;

/**
 * @author moxuanran 
 * 
 */
@Data
public class CacheValueHolder<V> implements Serializable {

    private static final long serialVersionUID = 1L;

    private V value;

    private long expireAfterWrite;

    public CacheValueHolder(V value, long expireAfterWrite) {
        this.value = value;
        this.expireAfterWrite = expireAfterWrite;
    }

}
