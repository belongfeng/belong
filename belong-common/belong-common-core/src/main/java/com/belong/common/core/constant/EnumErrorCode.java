package com.belong.common.core.constant;

import lombok.Getter;

import java.io.Serializable;

/**
* @Description:    全局异常码
* @Author:         fengyu
* @CreateDate:     2019/12/16 14:48
*/
@Getter
public enum EnumErrorCode implements IEnum {
    /**
     * 成功
     */
    SUCCESS(200, "成功" ),
    //400 (错误请求) 服务器不理解请求的语法。
    BAD_REQUEST(10001, "服务器不理解请求的语法" ),
    //404 (未找到) 服务器找不到请求的资源。
    NOT_FOUND(10002, "服务器找不到请求的资源" ),
    // 405 。(方法禁用) 禁用请求中指定的方法
    METHOD_NOT_ALLOWED(10003, "禁用请求中指定的方法" ),
    // 406 (不接受) 无法使用请求的内容特性响应请求的网页。
    NOT_ACCEPTABLE(10004, "无法使用请求的内容特性响应请求的网页" ),
    //415 (不支持的媒体类型) 请求的格式不受请求页面的支持。
    UNSUPPORTED_MEDIA_TYPE(10005, "请求的格式不受请求页面的支持" ),
    // 500 (服务器内部错误) 服务器遇到错误，无法完成请求。
    INTERNAL_SERVER_ERROR(10006, "服务器遇到错误，无法完成请求" ),
    // 401 (未授权) 请求要求身份验证。 (Basic 认证错误或无权限头)
    UNAUTHORIZED(10007, "请求要求身份验证。 (Basic 认证错误或无权限头)" ),
    // 403 (禁止) 服务器拒绝请求。
    FORBIDDEN(10005, "服务器拒绝请求" ),
    // 200 (请求成功,但是服务器拒绝操作) 操作不符合规范。
    CUSTOMEXCEPTION(10009, "操作不符合规范" ),
    // 200 (请求成功,但是服务器拒绝操作) 操作不符合规范。
    SITE_CONFLICT(10010, "场地冲突" ),
    // 400 字段校验错误
    INVALID_FIELD(20002, "字段校验错误" ),
    // 400 用户名,密码错误
    INVALID_GRANT(30001, "用户名或密码错误" ),
    // 403 用户被冻结
    DISABLED_USER(30002, "用户被冻结" ),
    // 400 密码错误
    INVALID_PWD(30003, "密码错误" ),
    // 400 用户已存在
    USER_EXIST(30101, "用户已存在" ),
    // 400 用户不存在
    USER_NOT_EXIST(30102, "用户不存在" ),
    // 403 短信发送太频繁
    SMS_TOO_MUCH(30103, "短信发送太频繁" ),
    // 400 数据已存在
    DATA_EXIST(30104, "数据已存在" ),
    // 400 无效验证码
    INVALID_CAPTCHA(30201, "无效验证码" ),
    //文件上传错误
    FileUploadError(40200, "文件上传失败" ),
    //短信发送失败
    apiSmsSendFailed(44100, "短信发送失败" ),
    //短信发送失败
    apiSmsSendFailed4ContentNull(44101, "短信发送失败" ),
    //短信发送失败
    apiSmsCodeInvalid(44110, "短信验证码错误" ),
    WX_ERROR(44111, "微信用户信息校验失败" );
    private Integer code;
    private String message;

    EnumErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMsgByCode(int code) {
        EnumErrorCode[] values = EnumErrorCode.values();
        for (EnumErrorCode ec : values) {
            if (ec.code == code) {
                return ec.message;
            }
        }
        return "" ;
    }

}
