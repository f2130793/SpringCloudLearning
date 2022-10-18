package com.moxuanran.learning.cache.support;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.concurrent.CountDownLatch;

/**
 * 缓存加载锁
 *
 * @author moxuanran 
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoaderLock<V> {

    private CountDownLatch signal;

    private Thread loaderThread;

    private volatile RuntimeException loaderException;

    private volatile V value;

}
