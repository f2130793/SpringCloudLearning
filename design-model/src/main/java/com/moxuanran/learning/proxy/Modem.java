package com.moxuanran.learning.proxy;

/**
 * @author wutao
 * @date 2022/9/28 15:21
 */
public class Modem implements Internet {
    private static final String PASSWORD = "123456";

    public Modem(String password) throws Exception{
        if (!PASSWORD.equals(password)) {
            throw new Exception("拨号失败，请重试");
        }
    }

    @Override
    public void httpAccess(String url) {
        System.out.println("正在访问" + url);
    }
}
