package com.belong.service.wechat.applet.base.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
@ConditionalOnClass(WxPayService.class)
@Slf4j
public class WxMaAutoConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public WxMaService wxMaService() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(env.getProperty("project.wx.applet.configs.appid"));
        config.setSecret(env.getProperty("project.wx.applet.configs.secret"));
        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(config);
        return service;
    }

    @Bean
    public WxPayService wxPayConfig() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(StringUtils.trimToNull(env.getProperty("project.wx.pay.appId")));
        payConfig.setMchId(StringUtils.trimToNull(env.getProperty("project.wx.pay.mchId")));
        payConfig.setMchKey(StringUtils.trimToNull(env.getProperty("project.wx.pay.mchKey")));
        payConfig.setKeyPath(StringUtils.trimToNull(env.getProperty("project.wx.pay.keyPath")));
        // 可以指定是否使用沙箱环境
        payConfig.setUseSandboxEnv(false);
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }

    /*private static Map<String, WxMaService> maServices = Maps.newHashMap();
    private static Map<String, WxPayService> payServices = Maps.newHashMap();*/

  /*  public static WxMaService getMaService(String appid) {
        WxMaService wxService = maServices.get(appid);
        if (wxService == null) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }
        return wxService;
    }

    public static WxPayService getPayService(String appid) {
        WxPayService payService = payServices.get(appid);
        if (payService == null) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }
        return payService;
    }*/

  /*  @Scheduled(cron = "0 0 12 * * ?")
    //@Scheduled(fixedDelay = 6000L)
    @PostConstruct
    public void init() {
        maServices.clear();
        payServices.clear();
        List<WxMiniappInfoDO> miniAppInfoList = wxMiniappInfoService.selectList(new EntityWrapper<>());

        maServices = miniAppInfoList.stream().filter(data->data.getAppId()!=null)
                .map(a -> {
                    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
                    config.setAppid(a.getAppId());
                    config.setSecret(a.getSecret());

                    WxMaService service = new WxMaServiceImpl();
                    service.setWxMaConfig(config);
                    return service;
                }).collect(Collectors.toMap(s -> s.getWxMaConfig().getAppid(), a -> a));

        payServices = miniAppInfoList.stream().filter(data->data.getMchId()!=null)
                .map(a -> {
                    WxPayConfig payConfig = new WxPayConfig();
                    payConfig.setAppId(StringUtils.trimToNull(a.getAppId()));
                    payConfig.setMchId(StringUtils.trimToNull(a.getMchId()));
                    payConfig.setMchKey(StringUtils.trimToNull(a.getMchKey()));
                    payConfig.setKeyPath(StringUtils.trimToNull(a.getKeyPath()));
                    // 可以指定是否使用沙箱环境
                    payConfig.setUseSandboxEnv(false);
                    WxPayService wxPayService = new WxPayServiceImpl();
                    wxPayService.setConfig(payConfig);
                    return wxPayService;
                }).collect(Collectors.toMap(s -> s.getConfig().getAppId(), a -> a));
    }*/
}
