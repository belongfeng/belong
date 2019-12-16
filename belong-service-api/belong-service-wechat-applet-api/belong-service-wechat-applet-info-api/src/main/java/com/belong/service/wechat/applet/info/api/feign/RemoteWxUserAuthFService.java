package com.belong.service.wechat.applet.info.api.feign;

import com.belong.common.core.base.ResponseVO;
import com.belong.common.core.constant.ServiceNameConstants;
import com.belong.service.wechat.applet.info.api.feign.factory.RemoteWxUserAuthFallbackFactory;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Classname RemoteWxUserAuthFService
 * @Description TODO
 * @Date 2019/12/16 16:30
 * @Created by FengYu
 */
@FeignClient(name = ServiceNameConstants.BELONG_SERVICE_WECHAT_APPLET_INFO, fallbackFactory = RemoteWxUserAuthFallbackFactory.class)
public interface RemoteWxUserAuthFService {
    @PostMapping("/v1/api/wxUserAuth/baseLogin")
    ResponseVO<WxUserInfoVO> baseLogin(@RequestBody String code);
}

