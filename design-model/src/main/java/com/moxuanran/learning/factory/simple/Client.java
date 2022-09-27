package com.moxuanran.learning.factory.simple;

import java.util.Random;

/**
 * @author wutao
 * @date 2022/9/27 09:40
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("游戏开始");
        //常规方法
//        int screenWidth = 100;
//        Random random = new Random();
//        int x = random.nextInt(screenWidth);
//        Enemy airplane = new Airplane(x,0);
//        airplane.show();
//
//        x = random.nextInt(screenWidth);
//        Enemy tank = new Tank(x,0);
//        tank.show();

        //简单工厂方法
        SimpleFactory factory = new SimpleFactory(100);
        factory.create("airplane").show();
        factory.create("tank").show();
    }
}
