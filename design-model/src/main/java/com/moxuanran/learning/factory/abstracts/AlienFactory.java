package com.moxuanran.learning.factory.abstracts;

/**
 * @author wutao
 * @date 2022/9/27 10:37
 */
public class AlienFactory implements AbstractFactory{
    private int x;
    private int y;

    public AlienFactory(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public LowClassUnit createLowClass() {
        LowClassUnit unit = new Roach(x,y);
        System.out.println("制造蟑螂兵成功");
        return unit;
    }

    @Override
    public MiddleClassUnit createMiddleClass() {
        MiddleClassUnit unit = new Poison(x,y);
        System.out.println("制造毒液兵成功");
        return unit;
    }

    @Override
    public HighClassUnit createHighClass() {
        HighClassUnit unit = new Mammoth(x, y);
        System.out.println("制造猛犸成功");
        return unit;
    }
}
