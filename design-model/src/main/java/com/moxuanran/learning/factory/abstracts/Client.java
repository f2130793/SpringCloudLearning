package com.moxuanran.learning.factory.abstracts;

/**
 * 抽象工厂模式
 *
 * @author wutao
 * @date 2022/9/27 10:44
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("游戏开始");
        System.out.println("双方挖矿攒钱");
        System.out.println("工人建造人类族工厂");
        AbstractFactory humanFactory = new HumanFactory(10,10);
        Unit marine = humanFactory.createLowClass();
        marine.show();
        Unit tank = humanFactory.createMiddleClass();
        tank.show();
        Unit ship = humanFactory.createHighClass();
        ship.show();

        System.out.println("外星");
        AbstractFactory alienFactory = new AlienFactory(200,200);
        Unit roach = alienFactory.createLowClass();
        roach.show();
        Unit poison = alienFactory.createMiddleClass();
        poison.show();
        Unit mammoth = alienFactory.createHighClass();
        mammoth.show();

        System.out.println("开始战斗");
        marine.attack();
        roach.attack();
        tank.attack();
        mammoth.attack();
        poison.attack();
        ship.attack();
    }
}
