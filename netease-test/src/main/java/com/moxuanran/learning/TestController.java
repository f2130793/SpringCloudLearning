package com.moxuanran.learning;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wutao
 * @date 2022/9/27 17:08
 */
@RestController
public class TestController {
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        String appKey = "297b1e9c72bb4c38";
        String secret = "948479a1e2d54732";
        String url = "https://openapi.8.163.com/common/v1/upload";

        ClientDemo.postFile(appKey,secret,url,file);
        return "";
    }
}
