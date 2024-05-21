package com.zh.usercenter.model.domain.request;

import lombok.Data;

/**
 * 用户登录请求体
 */
@Data
public class UserLoginRequest {
    public static final long serialVersionUID = 1L;
    // 账号
    private String userAccount;
    // 密码
    private String userPassword;
}
