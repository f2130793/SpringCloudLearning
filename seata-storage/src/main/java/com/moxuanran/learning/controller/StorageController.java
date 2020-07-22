package com.moxuanran.learning.controller;

import com.moxuanran.learning.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 莫轩然
 * @date 2020/7/21 15:15
 */
@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @RequestMapping("/decrease")
    public String decrease(@RequestParam Long productId, @RequestParam Integer count){
//        QueryWrapper<Storage> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("product_id",productId);
//        Storage one = storageService.getOne(queryWrapper);
//
//        Integer used = one.getUsed() + count;
//        Integer residue = one.getResidue() - count;
//        Storage storage = new Storage();
//        storage.setUsed(used);
//        storage.setResidue(residue);
//        storage.setId(one.getId());
//
//        storageService.updateById(storage);

        storageService.decrease(productId, count);

        return "扣减库存成功";
    }
}
