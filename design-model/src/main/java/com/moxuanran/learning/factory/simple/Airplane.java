package com.moxuanran.learning.factory.simple;

/**
 * @author wutao
 * @date 2022/9/27 09:37
 */
public class Airplane extends Enemy{
    public Airplane(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("绘制飞机在上层图层，出现坐标:" + x + "," + y);
        System.out.println("飞机向玩家发起进攻");
    }
}
