package com.moxuanran.learning;

import org.springframework.beans.factory.annotation.Autowired;
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
        String appKey = "";
        String secret = "";
        String url = "http://127.0.0.1:8080/common/v1/upload";

        ClientDemo.postFile(appKey,secret,url,file);
        return "";
    }
}
