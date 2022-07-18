package com.moxuran.learning.exception.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.moxuran.learning.BaseSerializable;
import com.moxuran.learning.config.BaseConfigBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 公共异常配置
 *
 * @author wuqiang
 */
public class ExConfigure extends BaseSerializable implements InitializingBean, EnvironmentAware {

    private static Logger log = LoggerFactory.getLogger(ExConfigure.class);

    private static final String CONFIG_APP_NAME = "spring.application.name";
    private static final String CONFIG_PREFIX = "error-code.application.";

    private BaseConfigBean baseConfigBean;
    /**
     * 应用码
     */
    private int applicationCode;

    /**
     * 配置文案
     */
    private List<Copyright> copyrights;

    /**
     * 应用定义文案
     */
    private List<Copyright> appCopyrights = Collections.emptyList();

    /**
     * 公共定义文案
     */
    private List<Copyright> commonCopyrights = Collections.emptyList();

    public ExConfigure(BaseConfigBean baseConfigBean) {
        this.baseConfigBean = baseConfigBean;
    }


    public int getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(int applicationCode) {
        this.applicationCode = applicationCode;
    }

    public BaseConfigBean getBaseConfigBean() {
        return baseConfigBean;
    }

    public void setBaseConfigBean(BaseConfigBean baseConfigBean) {
        this.baseConfigBean = baseConfigBean;
    }

    public List<Copyright> getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(List<Copyright> copyrights) {
        this.copyrights = copyrights;
    }

    public void resetCopyrights() {
        List<Copyright> copyrights = new ArrayList<>();
        copyrights.addAll(getCommonCopyrights());
        copyrights.addAll(getAppCopyrights());
        copyrights.sort(Comparator.comparingInt(Copyright::getPriority));
        this.copyrights = copyrights;
    }

    public List<Copyright> getAppCopyrights() {
        return appCopyrights;
    }

    public void setAppCopyrights(List<Copyright> appCopyrights) {
        this.appCopyrights = appCopyrights;
        resetCopyrights();
    }

    public List<Copyright> getCommonCopyrights() {
        return commonCopyrights;
    }

    public void setCommonCopyrights(List<Copyright> commonCopyrights) {
        this.commonCopyrights = commonCopyrights;
        resetCopyrights();
    }

    @Override
    public void afterPropertiesSet() {
        ExFactory.init(this);
    }

    @Override
    public void setEnvironment(Environment environment) {
        String applicationName = Binder.get(environment)
                .bind(CONFIG_APP_NAME, Bindable.of(String.class))
                .orElseGet(() -> {
                    throw new RuntimeException("[ EX STANDARD ] Application name undefined, config [" + CONFIG_APP_NAME + "] must be assigned");
                }).trim();
        applicationCode = Binder.get(environment)
                .bind(CONFIG_PREFIX + applicationName.toLowerCase(), Bindable.of(Integer.class))
                .orElseGet(() -> {
                    Integer newCode = autoAddApp(applicationName);
                    if (newCode != null) {
                        return newCode;
                    }
                    throw new RuntimeException("[ EX STANDARD ] Application code of [" + applicationName + "] undefined. Please check doc: https://danchuang.yuque.com/rwezph/rmedr4/oegdwc");
                });
        log.info("ExConfigure initialized > applicationName: {}, applicationCode: {}", applicationName, applicationCode);
    }

    private Integer autoAddApp(String appName) {
        Integer appCode = addAppCode(appName);
        if (appCode != null) {
            // 自动注册直连
            addAppRibbon(appName);
        }
        return appCode;
    }

    /**
     * 自动注册应用码
     * @param appName 应用名
     * @return 应用码
     */
    private Integer addAppCode(String appName) {
        String url = this.baseConfigBean.getQuickServer() + "/code/addApp";
        String param = "app=" + appName.toLowerCase();
        String result = sendGet(url, param);
        if (notBlank(result)) {
            JSONObject json = JSON.parseObject(result);
            Boolean success = json.getBoolean("success");
            if (success != null && success) {
                return json.getInteger("data");
            }
        }
        return null;
    }

    /**
     * 自动注册直连
     * @param appName 应用名
     */
    private void addAppRibbon(String appName) {
        String url = this.baseConfigBean.getQuickServer() + "/ribbon/addApp";
        String param = "app=" + appName.toLowerCase();
        String result = sendGet(url, param);
        if (notBlank(result)) {
            JSONObject json = JSON.parseObject(result);
            Boolean success = json.getBoolean("success");
            if (success == null || !success) {
                log.warn("Auto addAppRibbon failed, res: {}", json);
            }
        }
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    private static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

}
