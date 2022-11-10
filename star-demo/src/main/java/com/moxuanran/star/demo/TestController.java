package com.moxuanran.star.demo;

import com.netease.bchain.component.common.error.ErrorCode;
import com.netease.bchain.component.common.request.RestResult;
import com.netease.bchain.component.common.util.JacksonUtil;
import com.netease.bchain.project.basic.api.SampleClient;
import com.netease.bchain.project.basic.api.SampleFeignClient;
import com.netease.bchain.project.basic.api.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wutao
 * @date 2022/10/20 15:15
 */
@RequestMapping()
@RestController
public class TestController {
    @Autowired
    private SampleClient sampleClient;

    @Autowired
    private SampleFeignClient sampleFeignClient;

    @GetMapping("/test")
    public RestResult<Car> test() {
        Car car = new Car();
        car.setAge(1);
        car.setName("111");
        String s = JacksonUtil.toJson(car);
        System.out.println(s);

        RestResult<Car> result = RestResult.success(car);
        RestResult<Car> success = RestResult.success();
        System.out.println(success);
        System.out.println(result);
        RestResult<Car> result1 = RestResult.failure(ErrorCode.SERVER_ERROR);
        System.out.println(result1);

        return result1;
    }

    @GetMapping("/test2")
    public RestResult<Car> test2(String app) {
        Car car = new Car();
        car.setName("hhhh");
        return RestResult.success(car);
    }

    @GetMapping("/test3")
    public String test3() {
        return sampleClient.hello();
    }

    @GetMapping("/test4")
    public String test4() {
        MessageDto messageDto = new MessageDto();
        messageDto.setTitle("hello ");
        messageDto.setContent("world");
        return sampleClient.sendMessage(messageDto);
    }

    @GetMapping("/test5")
    public RestResult<String> test5() {
        MessageDto messageDto = new MessageDto();
        messageDto.setTitle("hello ");
        messageDto.setContent("world");
        return sampleFeignClient.sendMessage(messageDto);
    }

}
