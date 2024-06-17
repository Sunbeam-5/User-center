package com.zh.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.usercenter.model.domain.User;
import com.zh.usercenter.model.domain.request.UserLoginRequest;
import com.zh.usercenter.model.domain.request.UserRegisterRequest;
import com.zh.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.zh.usercenter.contant.UserContant.ADMIN_ROLE;
import static com.zh.usercenter.contant.UserContant.USER_LOGIN_STATE;

/**
 * 用户接口
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        return userService.userRegister(userAccount,userPassword,checkPassword);
    }
    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request, HttpServletResponse response) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        return userService.userLogin(userAccount,userPassword,request);
    }

//    @PostMapping("/logout")
//    public boolean userLogout(HttpServletRequest request) {
//        // return userService.userLogout(request);
//    }


    // 查询当前登录用户
    @GetMapping("/current")
    public User getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            return null;
        }
        Long userId = currentUser.getId();
        // todo: 校验用户状态是否异常
        User user = userService.getById(userId);
        return userService.getSafetyUser(user);
    }


    // 管理员查询
    @GetMapping("/search")
    public List<User> searchUser(String userName, HttpServletRequest request) {
        // return userService.searchUser(userName);
        // 仅管理员可查询
//        if (!userService.isAdmin(request)) {
//            return null;
//        }
       if (isAdmin(request)) {
           return new ArrayList<>();
       }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.like("username", userName);
        }
        List<User> userList = userService.list(queryWrapper);
        return userList.stream()
                .map(user -> userService.getSafetyUser(user)
                ).collect(Collectors.toList());
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id, HttpServletRequest request) {
        if (!isAdmin(request)) {
            return false;
        }
        if (id <= 0) {
            return false;
        }
        // return userService.deleteUser(id);
        return userService.removeById(id);
    }

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }
}
