package com.moxuanran.learning.decorator;


/**
 *装饰模式
 * @author wutao
 * @date 2022/9/27 14:58
 */
public class Client {
    public static void main(String[] args) {
       Showable makeUpGirl = new Lipstick(new FoundationMakeup(new Girl()));
       makeUpGirl.show();
    }
}
