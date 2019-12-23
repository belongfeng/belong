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
 */
@FeignClient(name = ServiceNameConstants.BELONG_SERVICE_WECHAT_APPLET_INFO, fallbackFactory = RemoteWxUserInfoDOFallbackFactory.class)
public interface RemoteWxUserInfoDOFService {
    @RequestMapping(method = RequestMethod.GET,value ="/v1/db/wxUserInfo/list",headers = {"Content-Type=application/x-www-form-urlencoded"})
    public ResponseVO<PageDataInfo<WxUserInfoListVO>> list(@RequestParam Map<String, Object> map);

    @RequestMapping(method = RequestMethod.POST,value ="/v1/db/wxUserInfo/saveOrUpdate",headers = {"Content-Type=application/json;charset=UTF-8"})
    public ResponseVO saveOrUpdate(@RequestBody WxUserInfoVO wxUserInfoVO);

    @RequestMapping(method = RequestMethod.GET,value ="/v1/db/wxUserInfo/get/{id}",headers = {"Content-Type=application/x-www-form-urlencoded"})
    public ResponseVO<WxUserInfoVO> get(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET,value ="/v1/db/wxUserInfo/remove/{id}",headers = {"Content-Type=application/x-www-form-urlencoded"})
    public ResponseVO remove(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET,value ="/v1/db/wxUserInfo/getWxUserInfoByOpenId/{openId}",headers = {"Content-Type=application/x-www-form-urlencoded"})
    public ResponseVO<WxUserInfoVO> getWxUserInfoByOpenId(@PathVariable("openId") String openId);
}
