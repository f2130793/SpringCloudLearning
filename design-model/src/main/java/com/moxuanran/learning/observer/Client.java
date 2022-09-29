package com.moxuanran.learning.observer;

/**
 * @author wutao
 * @date 2022/9/29 14:25
 */
public class Client {
    public static void main(String[] args) {
        Buyer buyer = new PhoneFans("手机粉");
        Buyer hand = new HandChopper("剁手族");
        Shop shop = new Shop();

        shop.register(buyer);
        shop.register(hand);

        shop.setProduct("猪肉炖粉条");
        shop.setProduct("手机");
    }
}
