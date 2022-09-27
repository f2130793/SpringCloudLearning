package com.moxuanran.learning.factory.abstracts;

/**
 * @author wutao
 * @date 2022/9/27 10:27
 */
public class Tank extends MiddleClassUnit{
    public Tank(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("坦克出现在坐标:[" + x + "," + y + "]");
    }

    @Override
    public void attack() {
        System.out.println("坦克用炮轰击，攻击力:" + attack);
    }
}
