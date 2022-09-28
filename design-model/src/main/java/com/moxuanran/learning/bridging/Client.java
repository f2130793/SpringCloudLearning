package com.moxuanran.learning.bridging;


/**
 * @author wutao
 * @date 2022/9/28 15:41
 */
public class Client {
    public static void main(String[] args) {
        Pen blackPen = new BlackPen(new CircleRuler());
        blackPen.draw();
    }
}
