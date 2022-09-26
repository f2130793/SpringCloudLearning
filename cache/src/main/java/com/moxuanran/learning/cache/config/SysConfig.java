package com.moxuanran.learning.cache.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 系统变量
 *
 * @author wutao
 * @date 2020/3/24 19:43
 */
@Configuration
@Data
public class SysConfig {

    private static SysConfig instance;

    public static SysConfig getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        SysConfig.instance = this;
    }

    @Value("${app.resource.domain:}")
    private String appResourceDomain;

    @Value("${jwt.secret:}")
    private String jwtSecret;

    @Value("${app.url.wapDomain:}")
    private String appUrlWapDomain;

    @Value("${app.wap.domain:}")
    private String appWapDomain;

    @Value("${switch.search.order.new:false}")
    private Boolean switchSearchOrderNew;

    @Value("${switch.default-data:false}")
    private Boolean switchDefaultData;

    @Value("${search.goods.url:}")
    private String searchGoodsUrl;

    @Value("${search.brands.url:}")
    private String searchBrandsUrl;

    @Value("${oms.api:}")
    private String omsApi;

    @Value("${oms.new.api:}")
    private String omsNewApi;

    @Value("${http.default.timeout:}")
    private Integer httpDefaultTimeout;

    @Value("${switch.use-multi-level-cache:false}")
    private Boolean switchUseMultiLevelCache;

    @Value("${switch.downgrade:}")
    private Boolean switchDowngrade;

    @Value("${switch.message.downgrade:false}")
    private Boolean switchMessageDowngrade;

    @Value("${thrift.message-service.url:}")
    private String thriftMessageServiceUrl;

    @Value("${message.service.url:}")
    private String messageServiceUrl;

    @Value("${sales.goods-price.url:}")
    private String salesGoodsPriceUrl;

    @Value("${pay.invoice.list:http://www.baidu.com}")
    private String payInvoiceList;

    @Value("${hystrix.withExecutionIsolationSemaphoreMaxConcurrentRequests:100}")
    private Integer hystrixWithExecutionIsolationSemaphoreMaxConcurrentRequests;

    @Value("${abm.redis.chatroomKey:rest_chatroom_}")
    private String chatroomKey;

    @Value("${cache.expireAfterWrite:1800000}")
    private Integer expireAfterWrite;

    @Value("${cache.localExpireAfterWrite:300000}")
    private Integer localExpireAfterWrite;

    @Value("${cache.keyPrefix:portal}")
    private String keyPrefix;

    @Value("${cache.expireRandom:30000}")
    private Integer expireRandom;

    @Value("${cache.localCacheLimit:1000}")
    private Integer localCacheLimit;

    @Value("${cache.downgradeExpireAfterWrite:300000}")
    private Integer downgradeExpireAfterWrite;

    @Value("${switch.open.cache:true}")
    private Boolean switchOpenCache;

    @Value("${cache.user-info.expire:600000}")
    private Long cacheUserInfoExpire;

    @Value("${cache.verify_token.expire:600000}")
    private Long cacheVerifyTokenExpire;

    @Value("${cache.user_provider.expire:600000}")
    private Long cacheUserProviderExpire;

    @Value("${switch.java.price:false}")
    private Boolean switchJavaPrice;

    @Value("${thrift.pool.connectTimeOut:5000}")
    private int thriftPoolConnectTimeOut;

    @Value("${thrift.pool.readTimeout:5000}")
    private int thriftpoolreadTimeout;

    @Value("${thrift.pool.maxTotal:20}")
    private int thriftPoolMaxTotal;

    @Value("${old.api.host:}")
    private String oldApiHost;

    @Value("${switch.use.verifyTokenPlus:false}")
    private Boolean switchUseVerifyTokenPlus;

    @Value("${switch.requestResubmitLock:true}")
    private Boolean switchRequestResubmitLock;

    @Value("${switch.popMessage.downgrade:false}")
    private Boolean switchPopMessageDowngrade;

    @Value("${cache.homePage.expireAfterWrite:7200000}")
    private Integer homePageExpireAfterWrite;

    @Value("${switch.use.dynamicDataSource:true}")
    private Boolean switchUseDynamicDataSource;

    /**
     * 分控系统url 默认：dev
     */
    @Value("${riskCenterService:http://192.168.6.9:5666}")
    private String riskCenterService;

    /**
     * 取消订单，白名单用户
     */
    @Value("${app.cancelOrder.whiteList:6927470,6927469,6927468,6927467,6927466,6927465,6927464,6927463,6927462}")
    private String cancelOrderWhiteList;

    /**
     * 分控系统url 默认：dev
     */
    @Value("${workOrderService:}")
    private String workOrderService;

    /**
     * 超时关单延长时间 默认不延长
     */
    @Value("${order.overtime.close.later:0}")
    private Integer closeOrderOvertime;

    @Value("${app.url.apiDomain:}")
    private String appUrlApiDomain;

    /**
     * 控制是否启用最新的税文案
     */
    @Value("${fee.type.config:false}")
    private Boolean FeeTypeConfigFlag;
    /**
     * 获取-最新到货，是否调用：新商品系统，灰度控制
     */
    @Value("${goods.new.latestArrival:false}")
    private Boolean goodsNewLatestArrivalFlag;

    /**
     * 获取-即将到货，是否调用：新商品系统，灰度控制
     */
    @Value("${goods.new.newArrival:false}")
    private Boolean goodsNewNewArrivalFlag;


    /**
     * 获取-首页-商品信息，是否调用：新商品系统，灰度控制
     */
    @Value("${goods.new.newHomePage:false}")
    private Boolean goodsNewHomePageFlag;

    /**
     * 获取-猜你喜欢，是否调用：新商品系统，灰度控制
     */
    @Value("${goods.new.newRecommend:false}")
    private Boolean goodsNewRecommendFlag;

    /**
     * 获取-黑卡订货，是否调用：新商品系统，灰度控制
     */
    @Value("${goods.new.newBlackOrder:false}")
    private Boolean goodsNewBlackOrderFlag;

    /**
     * 获取-商品列表，是否调用：新商品系统，灰度控制
     */
    @Value("${goods.new.newDoubleTag:false}")
    private Boolean goodsNewDoubleTagFlag;
    /**
     * 是否用底层图片服务的开关
     */
    @Value("${image.new.switch:false}")
    private Boolean imageNewSwitch;
    /**
     * 查询spu最多50个
     */
    @Value("${goods.new.searchSpu.limit:50}")
    private Integer searchSpuLimit;


    /**
     * portal批量取消订单数量限制
     */
    @Value("${portal.batch.cancelOrder.size:1000}")
    private Integer portalBatchCancelOrderSize;

    /**
     * 双10取消订单校验限制开关
     */
    @Value("${double10CancelOrderFlag:false}")
    private Boolean double10CancelOrderFlag;

    /**
     * 双10取消订单校验限制,开始时间
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    @Value("${double10CancelOrderStartTime:2020-10-10 00:00:00}")
    private String double10CancelOrderStartTime;

    /**
     * 双10取消订单校验限制,结束时间
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    @Value("${double10CancelOrderEndTime:2020-11-12 00:00:00}")
    private String double10CancelOrderEndTime;

    /**
     * 双10取消订单校验限制,订单升级等级
     */
    @Value("${double10CancelOrderUpgradeLevelList:2,4}")
    private String double10CancelOrderUpgradeLevelList;

    @Value("${wap-tools.host:}")
    private String wapToolsHost;

    @Value("${wap:}")
    private String wap;

    @Value("${toolsWap:}")
    private String toolsWap;

    /**
     * 复购信息缓存
     */
    @Value("${cache.user-repurchase-info.expire:600000}")
    private Long cacheUserRepurchaseInfoExpire;

    /**
     * 获取-订单商品SKU信息，是否调用：新商品系统，灰度控制
     */
    @Value("${goods.new.newOrder:false}")
    private Boolean goodsNewOrder;

    /**
     * 获取-查询订单拼团商品SPU信息，是否调用：新商品系统，灰度控制
     */
    @Value("${goods.new.newOrderGroup:false}")
    private Boolean goodsNewOrderGroup;

    @Value("${image.appid:abmau}")
    private String imageAppId;

}
