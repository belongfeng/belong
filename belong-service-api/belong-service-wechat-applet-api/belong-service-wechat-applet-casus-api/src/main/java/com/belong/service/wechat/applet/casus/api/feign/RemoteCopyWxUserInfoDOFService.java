package com.belong.service.wechat.applet.casus.api.feign;

import com.belong.common.core.base.ResponseVO;
import com.belong.common.core.constant.ServiceNameConstants;
import com.belong.common.core.page.PageDataInfo;
import com.belong.service.wechat.applet.casus.api.feign.factory.RemoteCopyWxUserInfoDOFallbackFactory;
import com.belong.service.wechat.applet.casus.api.vo.CopyWxUserInfoListVO;
import com.belong.service.wechat.applet.casus.api.vo.CopyWxUserInfoVO;
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
@FeignClient(name = ServiceNameConstants.BELONG_SERVICE_WECHAT_APPLET_CASUS, fallbackFactory = RemoteCopyWxUserInfoDOFallbackFactory.class)
public interface RemoteCopyWxUserInfoDOFService {
    @GetMapping("/v1/db/copyWxUserInfo/list")
    public ResponseVO<PageDataInfo<CopyWxUserInfoListVO>> list(@RequestParam Map<String, Object> map);

    @PostMapping("/v1/db/copyWxUserInfo/saveOrUpdate")
    public ResponseVO saveOrUpdate(@RequestBody CopyWxUserInfoVO copyWxUserInfoVO);

    @GetMapping("/v1/db/copyWxUserInfo/get/{id}")
    public ResponseVO<CopyWxUserInfoVO> get(@PathVariable("id") String id);

    @GetMapping("/v1/db/copyWxUserInfo/remove/{id}")
    public ResponseVO remove(@PathVariable("id") String id);

    @GetMapping("/v1/db/copyWxUserInfo/getWxUserInfoByOpenId/{openId}")
    public ResponseVO<CopyWxUserInfoVO> getWxUserInfoByOpenId(@PathVariable("openId") String openId);
}
