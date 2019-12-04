package com.belong.service.wechat.applet.info.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @Classname WeChatAppletLoginResult
 * @Description TODO
 * @Date 2019/12/4 14:33
 * @Created by FengYu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "WeChatAppletLoginResultVO", description = "微信小程序登录返回数据")
public class WeChatAppletLoginResultVO {
    @ApiModelProperty(name = "token", required = true)
    private String access_token;
    @ApiModelProperty(name = "expires_in", value = "过期时间（秒）", required = true)
    private Long expires_in;
    @ApiModelProperty(name = "token_type", value = "token类型", required = true)
    private String token_type;
    @ApiModelProperty(name = "userInfo", value = "微信用户信息")
    private WxUserInfoVO userInfo;
}
