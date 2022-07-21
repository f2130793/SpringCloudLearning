package com.moxuanran.learning.controller;

import com.moxuanran.learning.ExampleClient;
import com.moxuanran.learning.entity.dto.ExampleRpcDTO;
import com.moxuanran.learning.entity.vo.ExampleRpcVo;
import com.moxuran.learning.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author 莫轩然
 * @date 2020/7/15 11:25
 */
@Api("RPC相关案例")
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    ExampleClient exampleClient;

    @GetMapping("/getMsg")
    public JsonResult<String> getMsg(@RequestParam("msg") String msg) {
       return exampleClient.hello(msg);
    }

    @ApiOperation("测试创建example")
    @PostMapping("example/createExample")
    JsonResult<ExampleRpcVo> createExample(@RequestBody ExampleRpcDTO exampleRpcDTO){
        return exampleClient.createExample(exampleRpcDTO);
    }

    @ApiOperation("测试获取example")
    @GetMapping("/getExample")
    JsonResult<ExampleRpcVo> getExample(@RequestParam("id") Long id){
        return exampleClient.getExample(id);
    }

    @ApiOperation("测试异常抛出")
    @GetMapping("/getException")
    JsonResult<String> getException(){
        return exampleClient.getException();
    }

}
