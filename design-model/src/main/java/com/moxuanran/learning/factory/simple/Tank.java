package com.moxuanran.learning.factory.simple;

/**
 * @author wutao
 * @date 2022/9/27 09:37
 */
public class Tank extends Enemy{
    public Tank(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("绘制坦克在下层图层，出现坐标:" + x + "," + y);
        System.out.println("坦克向玩家发起进攻");
    }
}
