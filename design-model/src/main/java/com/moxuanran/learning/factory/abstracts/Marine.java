package com.moxuanran.learning.factory.abstracts;

/**
 * @author wutao
 * @date 2022/9/27 10:27
 */
public class Marine extends LowClassUnit{
    public Marine(int x, int y) {
        super(x, y);
    }

    @Override
    public void show() {
        System.out.println("士兵出现在坐标:[" + x + "," + y + "]");
    }

    @Override
    public void attack() {
        System.out.println("士兵用机关枪射击，攻击力:" + attack);
    }
}
