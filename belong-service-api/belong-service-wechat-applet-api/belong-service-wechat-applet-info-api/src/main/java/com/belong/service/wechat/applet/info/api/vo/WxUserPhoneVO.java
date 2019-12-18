package com.belong.service.wechat.applet.info.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @Description: 微信用户手机号信息
 * @Author: BelongFeng
 * @CreateDate: 2019-12-03 09:52:04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "WxUserPhoneVO", description = "微信用户手机号信息")
public class WxUserPhoneVO implements Serializable {
    private static final long serialVersionUID = -1L;
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;
    @ApiModelProperty(value = "脱敏以后的手机号")
    private String senPhoneNumber;
}
