package com.moxuanran.learning.factory.stand;

/**
 * @author wutao
 * @date 2022/9/27 10:07
 */
public class Client {
    public static void main(String[] args) {
        int screenWidth = 100;
        System.out.println("游戏开始");

        TankFactory tankFactory = new TankFactory();
        for (int i = 0; i < 5; i++) {
            tankFactory.create(screenWidth).show();
        }

        AirplaneFactory airplaneFactory = new AirplaneFactory();
        for (int i = 0; i < 5; i++) {
            airplaneFactory.create(screenWidth).show();
        }

        System.out.println("抵达关底");
        BossFactory bossFactory = new BossFactory();
        bossFactory.create(screenWidth).show();
    }
}
