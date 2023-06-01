package com.moxuanran.learning;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDemo {

    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    private static final HttpClient httpClient = HttpClients.createDefault();

    private static final String HEADER_CA_KEK = "x-ca-key";
    private static final String HEADER_CA_NONCE = "x-ca-nonce";
    private static final String HEADER_CA_TIMESTAMP = "x-ca-timestamp";
    private static final String HEADER_CA_SIGNATURE_HEADERS = "x-ca-signature-headers";
    private static final String HEADER_CA_SIGNATURE = "x-ca-signature";

    public static void addSign(HttpRequestBase httpRequest,
                                String appKey,
                                String secret,
                                String httpMethod,
                                String url,
                                String body,
                                Map<String, String[]> formParams) {
        long timestamp = System.currentTimeMillis();
        httpRequest.addHeader(HEADER_CA_KEK, appKey);
        httpRequest.addHeader(HEADER_CA_NONCE, timestamp + "");
        httpRequest.addHeader(HEADER_CA_TIMESTAMP, timestamp + "");

        Map<String, String> headMap = new HashMap<>();
        headMap.put(HEADER_CA_KEK, appKey);
        headMap.put(HEADER_CA_NONCE, timestamp + "");
        headMap.put(HEADER_CA_TIMESTAMP, timestamp + "");

        String[] signatureHeaders = headMap.keySet().toArray(new String[0]);
        String signature = SignatureUtils.signature(
                secret,
                httpMethod,
                url,
                formParams,
                body,
                signatureHeaders,
                headMap::get);
        httpRequest.addHeader(HEADER_CA_SIGNATURE, signature);
        httpRequest.addHeader(HEADER_CA_SIGNATURE_HEADERS, StringUtils.join(signatureHeaders, ","));
    }

    public static void get(String appKey, String secret, String url) throws Exception {
        HttpGet httpRequest = new HttpGet(url);
        String query = url.substring(url.indexOf("?") + 1);
        Map<String, String[]> map = new HashMap<>();
        for (String s : query.split("&")) {
            String[] kv = s.split("=");
            if (kv.length == 2) {
                map.put(kv[0], new String[]{kv[1]});
            }
        }
        addSign(httpRequest, appKey, secret, "GET", url, null, map);
        HttpResponse r = httpClient.execute(httpRequest);
        System.out.println("http code:" + r.getStatusLine().getStatusCode());
        System.out.println("response:" + EntityUtils.toString(r.getEntity(), StandardCharsets.UTF_8));
    }

    public static void postJson(String appKey, String secret, String url, String body) throws Exception {
        HttpPost httpRequest = new HttpPost(url);
        httpRequest.addHeader("content-type", APPLICATION_JSON);
        addSign(httpRequest, appKey, secret, "POST", url, body, null);
        StringEntity stringEntity = new StringEntity(body, StandardCharsets.UTF_8);
        httpRequest.setEntity(stringEntity);
        HttpResponse r = httpClient.execute(httpRequest);
        System.out.println("http code:" + r.getStatusLine().getStatusCode());
        System.out.println("response:" + EntityUtils.toString(r.getEntity(), StandardCharsets.UTF_8));
    }

    public static void postForm(String appKey, String secret, String url, Map<String, String[]> map) throws Exception {
        HttpPost httpRequest = new HttpPost(url);
        List<NameValuePair> pairList = new ArrayList<>();
        map.forEach((k, v) -> pairList.add(new BasicNameValuePair(k, v[0])));
        UrlEncodedFormEntity requestEntity = new UrlEncodedFormEntity(pairList, Consts.UTF_8.name());
        httpRequest.setEntity(requestEntity);

        // 加签名
        addSign(httpRequest, appKey, secret, "POST", url, null, map);

        HttpResponse r = httpClient.execute(httpRequest);
        System.out.println("http code:" + r.getStatusLine().getStatusCode());
        System.out.println("response:" + EntityUtils.toString(r.getEntity(), StandardCharsets.UTF_8));
    }

    public static void postFile(String appKey, String secret, String url, MultipartFile file) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        String fileName = file.getOriginalFilename();
        HttpEntity reqEntity = MultipartEntityBuilder.create().
                addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName)
                .build();
        httpPost.setEntity(reqEntity);

        // 加签名
        addSign(httpPost, appKey, secret, "POST", url, null, null);

        HttpResponse r = httpClient.execute(httpPost);
        System.out.println("http code:" + r.getStatusLine().getStatusCode());
        System.out.println("response:" + EntityUtils.toString(r.getEntity(), StandardCharsets.UTF_8));
    }

}