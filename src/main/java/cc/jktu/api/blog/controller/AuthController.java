package cc.jktu.api.blog.controller;

import cc.jktu.api.blog.annotation.ResponseMessage;
import cc.jktu.api.blog.dao.entity.User;
import cc.jktu.api.blog.dto.UserLoginResponse;
import cc.jktu.api.blog.dto.UserRegisterOrLoginRequest;
import cc.jktu.api.blog.exception.WrongPasswordException;
import cc.jktu.api.blog.service.UserService;
import cc.jktu.api.common.CommonResponse;
import cc.jktu.api.util.BcryptUtil;
import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseMessage("注册成功")
    public CommonResponse register(@RequestBody UserRegisterOrLoginRequest request) {
        final User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userService.addUser(user);
        return CommonResponse.noContent();
    }

    @PostMapping("/login")
    @ResponseMessage("登录成功")
    public UserLoginResponse login(@RequestBody UserRegisterOrLoginRequest request) {
        final User user = userService.getUserByUsername(request.getUsername());
        if (!BcryptUtil.checkPassword(request.getPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }
        StpUtil.login(user.getId());
        final UserLoginResponse response = new UserLoginResponse();
        response.setToken(StpUtil.getTokenValue());
        return response;
    }

    @PostMapping("/logout")
    @ResponseMessage("注销成功")
    public CommonResponse logout() {
        StpUtil.logout();
        return CommonResponse.noContent();
    }

}
