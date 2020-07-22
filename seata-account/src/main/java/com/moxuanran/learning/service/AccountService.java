package com.moxuanran.learning.service;

import com.moxuanran.learning.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author 莫轩然
 * @date 2020/7/21 16:29
 */
@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;

    public void decrease(Long userId, BigDecimal money){
        System.out.println("-------> account-server 开始扣余额");
        accountMapper.decrease(userId, money);
        System.out.println("-------> account-server 扣余额完成");
    }
}
