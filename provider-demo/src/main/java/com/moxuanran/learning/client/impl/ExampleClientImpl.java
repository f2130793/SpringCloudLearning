package com.moxuanran.learning.client.impl;

import com.moxuanran.learning.ExampleClient;
import com.moxuanran.learning.entity.dto.ExampleRpcDTO;
import com.moxuanran.learning.entity.vo.ExampleRpcVo;
import com.moxuanran.learning.po.Example;
import com.moxuanran.learning.service.ExampleService;
import com.moxuran.learning.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yjy
 * @date 2019/12/16 14:08
 **/
@Slf4j
@RestController
public class ExampleClientImpl implements ExampleClient {

    @Autowired
    private ExampleService exampleService;

    @Override
    public JsonResult<String> hello(String msg) {
        String reply = "received msg: " + msg;
        return JsonResult.success(reply);
    }

    @Override
    public JsonResult<ExampleRpcVo> createExample(ExampleRpcDTO exampleRpcDTO) {
        log.info("Get dto: {}", exampleRpcDTO);

        Example example = new Example();
        BeanUtils.copyProperties(exampleRpcDTO, example);
        example = exampleService.save(example);

        ExampleRpcVo exampleRpcVo = new ExampleRpcVo();
        BeanUtils.copyProperties(example, exampleRpcVo);
        return JsonResult.success(exampleRpcVo);
    }

    @Override
    public JsonResult<ExampleRpcVo> getExample(Long id) {
        Example example = exampleService.findById(id);
        if (example == null) {
            return null;
        }
        ExampleRpcVo exampleRpcVo = new ExampleRpcVo();
        BeanUtils.copyProperties(example, exampleRpcVo);
        return JsonResult.success(exampleRpcVo);
    }

    @Override
    public JsonResult<String> getException() {
        throw new RuntimeException("服务提供者报错了");
    }

}
