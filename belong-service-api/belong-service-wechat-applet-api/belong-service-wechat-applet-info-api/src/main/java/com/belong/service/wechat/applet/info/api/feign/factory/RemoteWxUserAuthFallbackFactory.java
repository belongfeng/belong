package com.belong.service.wechat.applet.info.api.feign.factory;

import com.belong.common.core.base.ResponseVO;
import com.belong.service.wechat.applet.info.api.feign.RemoteWxUserAuthFService;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class RemoteWxUserAuthFallbackFactory implements FallbackFactory<RemoteWxUserAuthFService> {
    @Override
    public RemoteWxUserAuthFService create(Throwable throwable) {
        log.error("回退原因{}", throwable.getMessage());
        return new RemoteWxUserAuthFService() {
            @Override
            public ResponseVO<WxUserInfoVO> baseLogin(String code) {
                return null;
            }
        };
    }
}
