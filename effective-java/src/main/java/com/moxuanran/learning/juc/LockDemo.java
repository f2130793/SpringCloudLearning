package com.moxuanran.learning.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author wutao
 * @date 2022/10/12 19:41
 */
public class LockDemo {
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public static void reset() {
        COUNTER.set(0);
    }

    public synchronized void wrong() {
        COUNTER.incrementAndGet();
    }

    public static void main(String[] args) {
        LockDemo.reset();
        LockDemo lockDemo = new LockDemo();
        IntStream.rangeClosed(1,1000000).parallel().forEach(i -> lockDemo.wrong());
        System.out.println(LockDemo.COUNTER);
    }
}
