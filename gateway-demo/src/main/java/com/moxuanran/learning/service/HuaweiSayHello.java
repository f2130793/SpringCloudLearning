package com.moxuanran.learning.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/8/23 14:06
 */
@Service
@ConditionalOnProperty(name = "mobile.provider",havingValue = "huawei")
public class HuaweiSayHello implements SayHello{
    @Override
    public String say(String name) {
        return "huawei" + name;
    }
}
