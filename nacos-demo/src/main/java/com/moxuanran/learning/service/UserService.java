package com.moxuanran.learning.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moxuanran.learning.entity.User;
import com.moxuanran.learning.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author 莫轩然
 * @date 2020/7/17 09:53
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
