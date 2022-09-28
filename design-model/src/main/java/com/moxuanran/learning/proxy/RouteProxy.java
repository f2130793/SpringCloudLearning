package com.moxuanran.learning.proxy;

import java.util.Arrays;
import java.util.List;

/**
 * @author wutao
 * @date 2022/9/28 15:25
 */
public class RouteProxy implements Internet {
    private final Internet modem;
    private final List<String> blackList = Arrays.asList("电影","游戏","音乐","小说");

    public RouteProxy() throws Exception {
        this.modem = new Modem("123456");
    }
    @Override
    public void httpAccess(String url) {
        for (String keyword : blackList) {
            if(url.contains(keyword)) {
                System.out.println("禁止访问：" + url);
                return;
            }
        }
        modem.httpAccess(url);
    }
}
