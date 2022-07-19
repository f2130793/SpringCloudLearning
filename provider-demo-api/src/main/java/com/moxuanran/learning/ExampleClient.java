package com.moxuanran.learning;

import com.moxuanran.learning.entity.dto.ExampleRpcDTO;
import com.moxuanran.learning.entity.vo.ExampleRpcVo;
import com.moxuran.learning.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * value = spring.application.name;  path = server.servlet.context-path
 * @author 莫轩然
 * @date 2022/7/18 15:58
 */
@FeignClient(value = "provider-demo")
public interface ExampleClient {
    @GetMapping("example/hello")
    JsonResult<String> hello(@RequestParam("msg") String msg);

    @PostMapping("example/createExample")
    JsonResult<ExampleRpcVo> createExample(@RequestBody ExampleRpcDTO exampleRpcDTO);

    @GetMapping("example/getExample")
    JsonResult<ExampleRpcVo> getExample(@RequestParam("id") Long id);

    @GetMapping("example/getException")
    JsonResult<String> getException();
}
