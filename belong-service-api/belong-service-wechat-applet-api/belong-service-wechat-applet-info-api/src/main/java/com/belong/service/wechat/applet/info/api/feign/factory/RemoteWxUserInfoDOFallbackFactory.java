package com.belong.service.wechat.applet.info.api.feign.factory;

import com.belong.common.core.base.ResponseVO;
import com.belong.service.wechat.applet.info.api.feign.RemoteWxUserInfoDOService;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteWxUserInfoDOFallbackFactory implements FallbackFactory<RemoteWxUserInfoDOService> {
    @Override
    public RemoteWxUserInfoDOService create(Throwable throwable) {
        log.error("回退原因{}", throwable.getMessage());
        return new RemoteWxUserInfoDOService() {

            @Override
            public ResponseVO<WxUserInfoVO> getWxUserInfoByOpenId(String openId) {
                return null;
            }
        };
    }
}
