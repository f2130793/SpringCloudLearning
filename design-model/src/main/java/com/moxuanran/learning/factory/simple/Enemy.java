package com.moxuanran.learning.factory.simple;

/**
 * 敌人
 *
 * @author wutao
 * @date 2022/9/27 09:35
 */
public abstract class Enemy {
    protected int x;
    protected int y;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 显示
     */
    public abstract void show();
}
