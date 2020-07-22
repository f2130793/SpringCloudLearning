package com.moxuanran.learning.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moxuanran.learning.dao.StorageDao;
import com.moxuanran.learning.entity.Storage;
import com.moxuanran.learning.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 莫轩然
 * @date 2020/7/21 15:08
 */
@Service
public class StorageService extends ServiceImpl<StorageMapper, Storage> {

    @Autowired
    private StorageDao storageDao;

    public Boolean decrease(Long productId,Integer count){
        System.out.println("-------> 开始扣库存");
        storageDao.decrease(productId, count);
        System.out.println("--------> 扣减库存结束");
        return true;
    }
}
