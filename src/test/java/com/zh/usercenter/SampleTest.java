package com.zh.usercenter;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.zh.usercenter.mapper.UserMapper;
import com.zh.usercenter.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
//        List<User> userList = userMapper.selectList(null);
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);
    }

}