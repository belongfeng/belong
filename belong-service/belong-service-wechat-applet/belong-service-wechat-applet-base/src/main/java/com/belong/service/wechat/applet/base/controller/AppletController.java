package com.belong.service.wechat.applet.base.controller;

import com.belong.common.core.base.BaseController;
import com.belong.service.wechat.applet.info.api.feign.RemoteWxUserInfoDOFService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Classname appletController
 * @Description TODO
 * @Date 2019/12/4 17:08
 * @Created by FengYu
 */
public class AppletController extends BaseController {
    /**
     * 用户服务
     */
    @Autowired
    protected RemoteWxUserInfoDOFService remoteWxUserInfoDOFService;
}
