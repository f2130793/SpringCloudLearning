package com.moxuanran.star.demo;

import com.netease.bchain.component.common.error.ErrorCode;
import com.netease.bchain.component.common.request.RestResult;
import com.netease.bchain.component.common.util.JacksonUtil;
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
}
