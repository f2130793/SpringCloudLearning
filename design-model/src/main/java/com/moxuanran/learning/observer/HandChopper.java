package com.moxuanran.learning.observer;

/**
 * @author wutao
 * @date 2022/9/29 14:23
 */
public class HandChopper extends Buyer {
    public HandChopper(String name) {
        super(name);
    }

    @Override
    public void inform(String product) {
        System.out.println(name);
        System.out.println("购买:" + product);
    }
}
