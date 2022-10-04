package com.moxuanran.learning.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {

	private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

	private final AtomicInteger mThreadNum = new AtomicInteger(1);

	private final String prefix;

	private final boolean isDaemon;

	private final ThreadGroup threadGroup;

	public NamedThreadFactory() {
		this("pool-" + POOL_SEQ.getAndIncrement(),false);
	}

	public NamedThreadFactory(String prefix) {
		this(prefix,false);
	}

	public NamedThreadFactory(String prefix, boolean daemon) {
		this.prefix = prefix + "-thread-";
		isDaemon = daemon;
        SecurityManager s = System.getSecurityManager();
		threadGroup = ( s == null ) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
	}

	public Thread newThread(Runnable runnable) {
		String name = prefix + mThreadNum.getAndIncrement();
        Thread ret = new Thread(threadGroup, runnable, name,0);
        ret.setDaemon(isDaemon);
        return ret;
	}

	public ThreadGroup getThreadGroup() {
		return threadGroup;
	}

}
