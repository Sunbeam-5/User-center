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
        user.setUsername("LXINN");
        user.setUserAccount("LXINN");
        user.setAvatarUrl("https://img.touxiangwu.com/uploads/allimg/2022053117/ivhiashhpu1.jpg");
        user.setGender(0);
        user.setUserPassword("Jay257248");
        user.setPhone("13984321671");
        user.setEmail("111111111@lx.com");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        // 1.密码不一致
        String userAccount = "LXINN";
        String userPassword = "";
        String checkPassword = "Jay257248";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        // 2.账号小于4位
        userAccount = "LX";
        userPassword = "Jay257248";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        // 3.密码小于8位
        userAccount = "LXINN";
        userPassword = "Jay257";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        // 4.特殊字符
        userAccount = "LX INN";
        userPassword = "Jay257248";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        // 5.密码和校验密码不同
        checkPassword = "Jay257248000";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        // 6.账号已存在
        userAccount = "LXINN";
        userPassword = "Jay257248";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        // 7.注册成功
        userAccount = "LXINN";
        userPassword = "Jay257248";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
    }
}