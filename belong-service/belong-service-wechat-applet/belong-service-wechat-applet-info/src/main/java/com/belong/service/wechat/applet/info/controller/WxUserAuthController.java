package com.belong.service.wechat.applet.info.controller;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.belong.common.core.base.ResponseVO;
import com.belong.common.exception.wxapplet.parameter.WxAppletParameterLossException;
import com.belong.service.wechat.applet.base.controller.AppletController;
import com.belong.service.wechat.applet.info.api.domain.WxUserInfoDO;
import com.belong.service.wechat.applet.info.api.vo.WeChatRegistryUserVO;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import com.belong.service.wechat.applet.info.service.IWxUserAuthService;
import com.mysql.cj.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * @Description: 微信小程序登录
 * @Author: fengyu
 * @CreateDate: 2019/12/4 14:27
 * @UpdateDate: 2019/12/4 14:27
 * @Version: 1.0
 */
@Api(tags = "微信用户登录")
@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/wxUserAuth")
@Slf4j
public class WxUserAuthController extends AppletController {
    @Autowired
    private final IWxUserAuthService wxUserAuthService;
    @Autowired
    private final RedisTemplate redisTemplate;

    /**
     * 方法实现说明:当仅获取code时得登录
     *
     * @param code
     * @return com.belong.common.core.base.ResponseVO<com.belong.service.wechat.applet.info.api.vo.WeChatAppletLoginResultVO>
     * @throws
     * @author fengyu
     * @date 2019/12/4 17:02
     */
    @PostMapping(value = "/baseLogin", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "code码登录")
    public ResponseVO<WxUserInfoVO> baseLogin(@RequestBody String code) {
        if (StringUtils.isNullOrEmpty(code)) {
            throw new WxAppletParameterLossException(new String[]{"code"});
        }
        WxMaJscode2SessionResult result = wxUserAuthService.wxUserLoginByCode(code);
        WxUserInfoDO wxUserInfoDO = wxUserAuthService.baseUserInfo(result);
        return ResponseVO.ok(generator.convert(wxUserInfoDO, WxUserInfoVO.class));
    }

    /**
     * @param registryUser
     * @return
     */
    @PostMapping(value = "/getUserInfo", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "获取微信用户信息")
    public ResponseVO getUserInfo(@RequestBody WeChatRegistryUserVO registryUser) {
        if (com.belong.common.util.StringUtils.isNull(registryUser)) {
            throw new WxAppletParameterLossException();
        }
        if (StringUtils.isNullOrEmpty(registryUser.getEncryptedData())) {
            throw new WxAppletParameterLossException(new String[]{"encryptedData"});
        }
        if (StringUtils.isNullOrEmpty(registryUser.getIv())) {
            throw new WxAppletParameterLossException(new String[]{"iv"});
        }
        String session_key = (String) redisTemplate.opsForValue().get(WxUserInfoDO.SESSION_KEY + getOpenId());
        if ("".equals(session_key)) {
            throw new WxAppletParameterLossException(new String[]{"session_key"});
        }
        WxUserInfoDO wxUserInfoDO = wxUserAuthService.userInfo(session_key, registryUser);
        if (com.belong.common.util.StringUtils.isNull(wxUserInfoDO)) {
            return ResponseVO.failed("获取用户信息失败！");
        }
        return ResponseVO.ok("获取用户信息成功！");
    }

    /**
     * @Description: 获取用户信息接口
     * @Author: fengyu
     * @CreateDate: 2019/12/5 11:01
     * @UpdateDate: 2019/12/5 11:01
     * @Version: 1.0
     */
    @ApiOperation(value = "获取用户信息接口")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/userInfo")
    public ResponseVO<WxUserInfoVO> info() {
        return ResponseVO.ok(getUserInfo());
    }

    @ApiOperation(value = "测试txlcn事务", notes = "权限标识 sys:wxUserInfo:view")
    //@PreAuthorize("hasAuthority('sys:wxUserInfo:view')")
    @GetMapping(value = "/txlcn/{oneId}/{twoId}")
    public ResponseVO<Boolean> tran(@ApiParam(required = true, value = "oneId") @PathVariable("oneId") String oneId, @ApiParam(required = true, value = "twoId") @PathVariable("twoId") String twoId) {

        return ResponseVO.ok(wxUserAuthService.tetLcn(oneId, twoId));
    }
}
