package com.moxuanran.learning.juc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * https://time.geekbang.org/column/article/209494
 *
 * @author wutao
 * @date 2022/10/12 17:04
 */
@Slf4j
public class ConcurrentHashMapDemo {
    private static final int THREAD_COUNT = 10;
    private static final int ITEM_COUNT = 1000;

    private static final int LOOP_COUNT = 10000000;

    /**
     * 获取指定数据容量的ConcurrentHashMap
     *
     * @param count 数
     * @return {@link ConcurrentHashMap}<{@link String},{@link Long}>
     */
    private ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream
                .rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
    }

    /**
     * 错误使用示例
     * ConcurrentHashMap并不能保证复合操作的原子性，另外size()方法在多线程下获取到仅仅是一个参考值
     * 正确示范为加同步锁
     */
    public void wrong() throws InterruptedException {
        ConcurrentHashMap<String, Long> data = getData(ITEM_COUNT - 100);
        log.info("init size:{}", data.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            //查询还需要补充多少个元素
            int gap = ITEM_COUNT - data.size();
            log.info("gap size:{}", gap);
            //补充元素
            data.putAll(getData(gap));
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        log.info("finish size:{}", data.size());
    }

    public Map<String,Long> normalUse() throws InterruptedException {
        ConcurrentHashMap<String,Long> data = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1,LOOP_COUNT).parallel().forEach(i -> {
            String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
            synchronized (data) {
                if(data.containsKey(key)) {
                    data.put(key,data.get(key) + 1L);
                }else {
                    data.put(key,1L);
                }
            }
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1,TimeUnit.HOURS);
        return data;
    }

    public Map<String,Long> goodUse() throws InterruptedException {
        ConcurrentHashMap<String,LongAdder> data = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1,LOOP_COUNT).parallel().forEach(i -> {
            String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
            data.computeIfAbsent(key,k -> new LongAdder()).increment();
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1,TimeUnit.HOURS);
        return data.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().longValue()));
    }

    public static void main(String[] args) throws InterruptedException {
        //1.错误使用导致数据不准确
        ConcurrentHashMapDemo demo = new ConcurrentHashMapDemo();
//        demo.wrong();
        //2.比较加锁和CAS两种方式的损耗
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("normal-use");
        Map<String, Long> normalUse = demo.normalUse();
        stopWatch.stop();
        Assert.isTrue(normalUse.size() == ITEM_COUNT,"normal use error");
        Assert.isTrue(normalUse.values().stream().mapToLong(Long::longValue).sum() == LOOP_COUNT,"normal count error");
        stopWatch.start("good-use");
        Map<String, Long> goodUse = demo.goodUse();
        stopWatch.stop();
        Assert.isTrue(goodUse.size() == ITEM_COUNT,"good use error");
        Assert.isTrue(goodUse.values().stream().mapToLong(Long::longValue).sum() == LOOP_COUNT,"good count error");
        log.info(stopWatch.prettyPrint());
    }
}
