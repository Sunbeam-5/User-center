package com.zh.usercenter;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class UserCenterApplicationTests {

    // 加密测试
    @Test
    void testDigest() {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(md5.digest("123456".getBytes()));
    }

    // 加密测试
    @Test
    void testDigest1() {
        String newPassword = DigestUtils.md5DigestAsHex(("abcd" + "mypassword").getBytes(StandardCharsets.UTF_8));
        System.out.println(newPassword);
    }

    @Test
    void contextLoads() {
    }

}
