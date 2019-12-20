package com.belong.service.wechat.applet.casus.api.feign.factory;

import com.belong.common.core.page.PageDataInfo;
import com.belong.service.wechat.applet.casus.api.feign.RemoteWxUserCasusDOFService;
import com.belong.service.wechat.applet.casus.api.vo.WxUserCasusListVO;
import com.belong.service.wechat.applet.casus.api.vo.WxUserCasusVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.belong.common.core.base.ResponseVO;

import java.util.Map;

/**
 * @Description:     案列
 * @Author:          BelongFeng
 * @CreateDate:      2019-12-13 10:42:09
 */
@Slf4j
@Component
public class RemoteWxUserCasusDOFallbackFactory implements FallbackFactory<RemoteWxUserCasusDOFService> {
    @Override
    public RemoteWxUserCasusDOFService create(Throwable throwable) {
        log.error("回退原因{}", throwable.getMessage());
        return new RemoteWxUserCasusDOFService() {
            @Override
            public ResponseVO<PageDataInfo<WxUserCasusListVO>> list(Map<String, Object> map) {
                return null;
            }

            @Override
            public ResponseVO saveOrUpdate(WxUserCasusVO wxUserCasusVO) {
                return null;
            }

            @Override
            public ResponseVO<WxUserCasusVO> get(String id) {
                return null;
            }

            @Override
            public ResponseVO remove(String id) {
                return null;
            }
        };
    }
}