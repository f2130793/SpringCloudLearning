package com.moxuanran.learning.factory.abstracts;

/**
 * @author wutao
 * @date 2022/9/27 10:37
 */
public class HumanFactory implements AbstractFactory{
    private int x;
    private int y;

    public HumanFactory(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public LowClassUnit createLowClass() {
        LowClassUnit unit = new Marine(x,y);
        System.out.println("制造海军陆战队队员成功");
        return unit;
    }

    @Override
    public MiddleClassUnit createMiddleClass() {
        MiddleClassUnit unit = new Tank(x,y);
        System.out.println("制造变形坦克成功");
        return unit;
    }

    @Override
    public HighClassUnit createHighClass() {
        HighClassUnit unit = new Battleship(x, y);
        System.out.println("制造战舰成功");
        return unit;
    }
}
