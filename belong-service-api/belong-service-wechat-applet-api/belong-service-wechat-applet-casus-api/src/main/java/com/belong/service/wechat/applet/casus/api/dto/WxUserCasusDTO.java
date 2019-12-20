package com.belong.service.wechat.applet.casus.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 案列
 * @Author: BelongFeng
 * @CreateDate: 2019-12-13 10:42:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "WxUserCasusDTO", description = "案列")
public class WxUserCasusDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "微信用户主键", required = true)
    private String wxUserId;
    @ApiModelProperty(value = "申请主键", required = true)
    private String applyId;
    @ApiModelProperty(value = "案列介绍", required = true)
    private String introduced;
    @ApiModelProperty(value = "备注 备注", required = true)
    private String remark;
}
