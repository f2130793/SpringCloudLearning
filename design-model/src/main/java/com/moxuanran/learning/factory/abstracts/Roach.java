package com.moxuanran.learning.factory.abstracts;

/**
 * @author wutao
 * @date 2022/9/27 10:27
 */
public class Roach extends LowClassUnit{
    public Roach(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("蟑螂兵出现在坐标:[" + x + "," + y + "]");
    }

    @Override
    public void attack() {
        System.out.println("蟑螂兵用爪子攻击，攻击力:" + attack);
    }
}
