package com.moxuanran.learning.service.impl;

import com.moxuanran.learning.mapper.ExampleMapper;
import com.moxuanran.learning.po.Example;
import com.moxuanran.learning.service.ExampleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wutao
 * @date 2019/12/16 16:02
 **/
@Service
public class ExampleServiceImpl implements ExampleService {

    @Resource
    private ExampleMapper exampleMapper;

    @Override
    public Example save(Example example) {
        exampleMapper.insert(example);
        return example;
    }

    @Override
    public Example findById(Long id) {
        return exampleMapper.selectById(id);
    }


}
