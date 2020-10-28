package com.moxuanran.learning.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;

/**
 * @author 莫轩然
 * @date 2020/9/2 10:12
 */
@Component
public class Signature {
    public String createSign() throws Exception {
        Long timestamp = System.currentTimeMillis();
        String appSecret = "SECec68e8a003249515fc9c4afaccb432963d67bcfd27618cc791eeb739004a75e2";
        String stringToSign = timestamp + "\n" + appSecret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(appSecret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));

        return URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
    }
}
