package com.moxuanran.learning.strategy;

/**
 * @author wutao
 * @date 2022/9/29 10:17
 */
public class Mouse implements USB {
    @Override
    public void read() {
        System.out.println("鼠标指令数据.......");
    }
}
