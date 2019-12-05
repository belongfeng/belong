package com.belong.service.wechat.applet.info.api.feign.factory;

import com.belong.common.core.base.ResponseVO;
import com.belong.common.core.page.PageDataInfo;
import com.belong.service.wechat.applet.info.api.feign.RemoteWxUserInfoDOFService;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoListVO;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class RemoteWxUserInfoDOFallbackFactory implements FallbackFactory<RemoteWxUserInfoDOFService> {
    @Override
    public RemoteWxUserInfoDOFService create(Throwable throwable) {
        log.error("回退原因{}", throwable.getMessage());
        return new RemoteWxUserInfoDOFService() {
            @Override
            public ResponseVO<PageDataInfo<WxUserInfoListVO>> list(Map<String, Object> map) {
                return null;
            }

            @Override
            public ResponseVO saveOrUpdate(WxUserInfoVO wxUserInfoVO) {
                return null;
            }

            @Override
            public ResponseVO<WxUserInfoVO> get(String id) {
                return null;
            }

            @Override
            public ResponseVO remove(String id) {
                return null;
            }

            @Override
            public ResponseVO<WxUserInfoVO> getWxUserInfoByOpenId(String openId) {
                return null;
            }
        };
    }
}
