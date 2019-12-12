package com.belong.service.wechat.applet.casus.api.feign.factory;

import com.belong.common.core.base.ResponseVO;
import com.belong.common.core.page.PageDataInfo;
import com.belong.service.wechat.applet.casus.api.feign.RemoteCopyWxUserInfoDOFService;
import com.belong.service.wechat.applet.casus.api.vo.CopyWxUserInfoListVO;
import com.belong.service.wechat.applet.casus.api.vo.CopyWxUserInfoVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class RemoteCopyWxUserInfoDOFallbackFactory implements FallbackFactory<RemoteCopyWxUserInfoDOFService> {
    @Override
    public RemoteCopyWxUserInfoDOFService create(Throwable throwable) {
        log.error("回退原因{}", throwable.getMessage());
        return new RemoteCopyWxUserInfoDOFService() {
            @Override
            public ResponseVO<PageDataInfo<CopyWxUserInfoListVO>> list(Map<String, Object> map) {
                return null;
            }

            @Override
            public ResponseVO saveOrUpdate(CopyWxUserInfoVO copyWxUserInfoVO) {
                return null;
            }

            @Override
            public ResponseVO<CopyWxUserInfoVO> get(String id) {
                return null;
            }

            @Override
            public ResponseVO remove(String id) {
                return null;
            }

            @Override
            public ResponseVO<CopyWxUserInfoVO> getWxUserInfoByOpenId(String openId) {
                return null;
            }
        };
    }
}
