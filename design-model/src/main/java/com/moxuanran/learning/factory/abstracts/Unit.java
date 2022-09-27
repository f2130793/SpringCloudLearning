package com.moxuanran.learning.factory.abstracts;

/**
 * @author wutao
 * @date 2022/9/27 10:20
 */
public abstract class Unit {
    protected int attack;
    protected int defence;
    protected int health;
    protected int x;
    protected int y;

    public Unit(int attack, int defence, int health, int x, int y) {
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.x = x;
        this.y = y;
    }

    /**
     * 显示
     */
    public abstract void show();

    /**
     * 攻击
     */
    public abstract void attack();
}
