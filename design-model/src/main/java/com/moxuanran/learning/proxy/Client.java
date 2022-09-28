package com.moxuanran.learning.proxy;

/**
 * @author wutao
 * @date 2022/9/28 15:29
 */
public class Client {
    public static void main(String[] args) throws Exception{
        Internet proxy = new RouteProxy();
        proxy.httpAccess("http://www.电影.com");
        proxy.httpAccess("http://www.工作.com");
    }
}
