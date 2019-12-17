package com.belong.service.wechat.applet.info.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "WeChatRegistryUserVO", description = "微信小程序用户加密数据")
public class WeChatRegistryUserVO implements Serializable {
    /**
     * 包括敏感数据在内的完整用户信息的加密数据
     */
    @ApiModelProperty(name = "encryptedData", value = "包括敏感数据在内的完整用户信息的加密数据", required = true)
    private String encryptedData;
    /**
     * 加密算法的初始向量
     */
    @ApiModelProperty(name = "iv", value = "加密算法的初始向量", required = true)
    private String iv;
}
