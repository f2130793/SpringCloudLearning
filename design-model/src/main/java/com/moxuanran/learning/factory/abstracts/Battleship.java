package com.moxuanran.learning.factory.abstracts;

/**
 * @author wutao
 * @date 2022/9/27 10:27
 */
public class Battleship extends HighClassUnit{
    public Battleship(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("战舰出现在坐标:[" + x + "," + y + "]");
    }

    @Override
    public void attack() {
        System.out.println("战舰用激光炮打击，攻击力:" + attack);
    }
}
