package com.zh.usercenter.service;

import com.zh.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 19855
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-04-25 16:11:36
 * 用户服务接口
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);
}
