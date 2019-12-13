package com.belong.service.wechat.applet.casus.api.feign;
import com.belong.service.wechat.applet.casus.api.feign.factory.RemoteWxUserCasusDOFallbackFactory;
import com.belong.service.wechat.applet.casus.api.vo.WxUserCasusListVO;
import com.belong.service.wechat.applet.casus.api.vo.WxUserCasusVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.belong.common.core.base.ResponseVO;
import com.belong.common.core.constant.ServiceNameConstants;
import org.springframework.web.bind.annotation.RequestParam;
import com.belong.common.core.page.PageDataInfo;

import java.util.Map;

/**
 * @Description:     案列
 * @Author:          BelongFeng
 * @CreateDate:      2019-12-13 10:42:09
 * @UpdateDate:      2019-12-13 10:42:09
 * @Version: 1.0
 */
@FeignClient(name = ServiceNameConstants.BELONG_SERVICE_WECHAT_APPLET_CASUS, fallbackFactory = RemoteWxUserCasusDOFallbackFactory.class)
public interface RemoteWxUserCasusDOFService {
    @GetMapping("/v1/db/wxUserCasus/list")
    public ResponseVO<PageDataInfo<WxUserCasusListVO>> list(@RequestParam Map<String, Object> map);

    @PostMapping("/v1/db/wxUserCasus/saveOrUpdate")
    public ResponseVO saveOrUpdate(@RequestBody WxUserCasusVO wxUserCasusVO);

    @GetMapping("/v1/db/wxUserCasus/get/{id}")
    public ResponseVO<WxUserCasusVO> get(@PathVariable("id") String id);

    @GetMapping("/v1/db/wxUserCasus/remove/{id}")
    public ResponseVO remove(@PathVariable("id") String id);

}
