package com.moxuanran.learning.factory.simple;

import java.util.Random;

/**
 * @author wutao
 * @date 2022/9/27 09:43
 */
public class SimpleFactory {
    private final int screenWidth;
    private final Random random;

    public SimpleFactory(int screenWidth) {
        this.screenWidth = screenWidth;
        this.random = new Random();
    }

    public Enemy create(String type) {
        int x = random.nextInt(screenWidth);
        Enemy enemy = null;
        switch (type) {
            case "tank":
                 enemy = new Tank(x,0);
                 break;
            case "airplane":
                enemy = new Airplane(x,0);
                break;
            default:
                break;
        }

        return enemy;
    }
}
