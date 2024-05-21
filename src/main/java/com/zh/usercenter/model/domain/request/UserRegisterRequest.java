package com.zh.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 */
@Data
public class UserRegisterRequest implements Serializable {
    public static final long serialVersionUID = 1L;
    // 账号
    private String userAccount;
    // 密码
    private String userPassword;
    // 校验密码
    private String checkPassword;
}
