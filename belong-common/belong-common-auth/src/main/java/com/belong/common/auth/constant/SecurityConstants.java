package com.belong.common.auth.constant;

/**
 * @Classname SecurityConstants
 * @Description TODO
 * @Date 2020/1/7 13:48
 * @Created by FengYu
 */
public interface SecurityConstants {
    /**
     * 刷新
     */
    String REFRESH_TOKEN = "refresh_token";
    /**
     * 验证码有效期
     */
    int CODE_TIME = 60;
    /**
     * 验证码长度
     */
    String CODE_SIZE = "4";
    /**
     * 角色前缀
     */
    String ROLE = "ROLE_";
    /**
     * 前缀
     */
    String BELONG_PREFIX = "Belong_";

    /**
     * oauth 相关前缀
     */
    String OAUTH_PREFIX = "oauth:";
    /**
     * 客户端模式
     */
    String CLIENT_CREDENTIALS = "client_credentials";

    /**
     * 用户ID字段
     */
    String DETAILS_USER_ID = "user_id";

    /**
     * 用户名字段
     */
    String DETAILS_USERNAME = "username";

    /**
     * 用户部门字段
     */
    String DETAILS_DEPT_ID = "dept_id";

    /**
     * 租户ID 字段
     */
    String DETAILS_TENANT_ID = "tenant_id";
}
