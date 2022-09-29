package com.moxuanran.learning.observer;

/**
 * @author wutao
 * @date 2022/9/29 14:17
 */
public abstract class Buyer {
    protected String name;
    public Buyer(String name) {
        this.name = name;
    }

    public abstract void inform(String product);
}
