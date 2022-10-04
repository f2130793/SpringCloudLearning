package com.moxuanran.learning.factory.abstracts;

/**
 * @author wutao
 * @date 2022/9/27 10:27
 */
public class Poison extends MiddleClassUnit{
    public Poison(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("毒液兵出现在坐标:[" + x + "," + y + "]");
    }

    @Override
    public void attack() {
        System.out.println("毒液兵用毒液喷射，攻击力:" + attack);
    }
}
