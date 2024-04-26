package com.zh.usercenter.service;

import com.zh.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;


/**
 * 用户服务测试
 */
@ActiveProfiles("test")
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;
    @Test
    void testAdd() {
        User user = new User();
        user.setUsername("zh");
        user.setUserAccount("123");
        user.setAvatarUrl("https://img.touxiangwu.com/uploads/allimg/2022053117/ivhiashhpu1.jpg");
        user.setGender(0);
        user.setUserPassword("12345678");
        user.setPhone("123456789");
        user.setEmail("111111111");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        // 密码不一致
        String userAccount = "LXINN";
        String userPassword = "";
        String checkPassword = "Jay257248";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        // 账号小于4位
        userAccount = "LX";
        userPassword = "Jay257248";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        // 密码小于8位
        userAccount = "LXINN";
        userPassword = "Jay257";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        // 特殊字符
        userAccount = "LX INN";
        userPassword = "Jay257248";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        // 密码和校验密码不同
        userAccount = "LXINN";
        checkPassword = "Jay257248000";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        // 账号已存在
        userAccount = "test";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        // 注册成功
        userAccount = "LXINN";
        userPassword = "Jay257248";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
    }
}