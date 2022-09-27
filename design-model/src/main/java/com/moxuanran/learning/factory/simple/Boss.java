package com.moxuanran.learning.factory.simple;

/**
 * @author wutao
 * @date 2022/9/27 09:37
 */
public class Boss extends Enemy{
    public Boss(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("Boss出现，出现坐标:" + x + "," + y);
        System.out.println("Boss向玩家发起进攻");
    }
}
