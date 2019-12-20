package com.belong.service.wechat.applet.casus.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@ApiModel(value = "WxUserCasusVO", description = "案列详情")
public class WxUserCasusVO implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "微信用户主键")
    private String wxUserId;
    @ApiModelProperty(value = "申请主键")
    private String applyId;
    @ApiModelProperty(value = "案列介绍")
    private String introduced;
    @ApiModelProperty(value = "备注 备注")
    private String remark;
}
