package com.belong.service.wechat.applet.info.api.feign;

import com.belong.common.core.base.ResponseVO;
import com.belong.common.core.constant.ServiceNameConstants;
import com.belong.common.core.page.PageDataInfo;
import com.belong.service.wechat.applet.info.api.feign.factory.RemoteWxUserInfoDOFallbackFactory;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoListVO;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description: 微信用户信息Feign服务层
 * @Author: fengyu
 * @CreateDate: 2019/12/4 11:12
 * @UpdateDate: 2019/12/4 11:12
 * @Version: 1.0
 */
@FeignClient(name = ServiceNameConstants.BELONG_SERVICE_WECHAT_APPLET_INFO, fallbackFactory = RemoteWxUserInfoDOFallbackFactory.class)
public interface RemoteWxUserInfoDOFService {
    @GetMapping("/v1/db/wxUserInfo/list")
    public ResponseVO<PageDataInfo<WxUserInfoListVO>> list(@RequestParam Map<String, Object> map);

    @PostMapping("/v1/db/wxUserInfo/saveOrUpdate")
    public ResponseVO saveOrUpdate(@RequestBody WxUserInfoVO wxUserInfoVO);

    @GetMapping("/v1/db/wxUserInfo/get/{id}")
    public ResponseVO<WxUserInfoVO> get(@PathVariable("id") String id);

    @GetMapping("/v1/db/wxUserInfo/remove/{id}")
    public ResponseVO remove(@PathVariable("id") String id);

    @GetMapping("/v1/db/wxUserInfo/getWxUserInfoByOpenId/{openId}")
    public ResponseVO<WxUserInfoVO> getWxUserInfoByOpenId(@PathVariable("openId") String openId);
}
