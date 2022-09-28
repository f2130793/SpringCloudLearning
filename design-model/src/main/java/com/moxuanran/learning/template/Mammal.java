package com.moxuanran.learning.template;

/**
 * @author wutao
 * @date 2022/9/28 16:19
 */
public abstract class Mammal {
    /**
     * 移动
     */
    public abstract void move();

    /**
     * 吃
     */
    public abstract void eat();

    /**
     * 固化不可变模板
     */
    public final void live() {
        move();
        eat();
    }
}
