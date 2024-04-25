package com.zh.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.usercenter.model.domain.User;
import com.zh.usercenter.service.UserService;
import com.zh.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 19855
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-04-25 16:11:36
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




