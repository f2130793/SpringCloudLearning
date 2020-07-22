package com.moxuanran.learning.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * HystrixCommand中的常用参数
 * fallbackMethod：指定服务降级处理方法；
 * ignoreExceptions：忽略某些异常，不发生服务降级；
 * commandKey：命令名称，用于区分不同的命令；
 * groupKey：分组名称，Hystrix会根据不同的分组来统计命令的告警及仪表盘信息；
 * threadPoolKey：线程池名称，用于划分线程池。
 * <p>
 * <p>
 * 缓存使用
 * CacheResult：开启缓存，默认所有参数作为缓存的key，cacheKeyMethod可以通过返回String类型的方法指定key；
 * CacheKey：指定缓存的key，可以指定参数或指定参数中的属性值为缓存key，cacheKeyMethod还可以通过返回String类型的方法指定；
 * CacheRemove：移除缓存，需要指定commandKey。
 * <p>
 * 请求合并HystrixCollapser
 * batchMethod：用于设置请求合并的方法；
 * collapserProperties：请求合并属性，用于控制实例属性，有很多；
 * timerDelayInMilliseconds：collapserProperties中的属性，用于控制每隔多少时间合并一次请求；
 *
 * @author 莫轩然
 * @date 2020/7/16 11:07
 */
@Service
public class HystrixTestService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getDefault")
    public String get(String str) {
        return restTemplate.getForObject("http://provider-demo-server/test/say?str=3333", String.class);
    }

    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(fallbackMethod = "getDefault", commandKey = "getCacheKey")
    public String getCache(String str) {
        return restTemplate.getForObject("http://provider-demo-server" + "/test/say?str=4444", String.class);
    }

    public String getCacheKey(String str) {
        return str;
    }

    /**
     * 断路器默认方法，参数必须与方法一致
     *
     * @param str 字符串参数
     * @return string
     */
    public String getDefault(String str) {
        return "服务忙，请稍后再试" + str;
    }

}
