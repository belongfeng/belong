package com.belong.service.wechat.applet.info.api.feign;

import com.belong.common.core.base.ResponseVO;
import com.belong.common.core.constant.ServiceNameConstants;
import com.belong.service.wechat.applet.info.api.feign.factory.RemoteWxUserInfoDOFallbackFactory;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 微信用户信息Feign服务层
 * @Author: fengyu
 * @CreateDate: 2019/12/4 11:12
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/12/4 11:12
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@FeignClient(name = ServiceNameConstants.BELONG_SERVICE_WECHAT_APPLET_INFO, fallbackFactory = RemoteWxUserInfoDOFallbackFactory.class)
public interface RemoteWxUserInfoDOService {
    @GetMapping("/v1/db/wxUserInfo/getWxUserInfoByOpenId/{openId}")
    public ResponseVO<WxUserInfoVO> getWxUserInfoByOpenId(@RequestParam("openId") String openId);

}
