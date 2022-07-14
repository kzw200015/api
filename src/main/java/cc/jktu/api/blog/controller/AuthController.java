package cc.jktu.api.blog.controller;

import cc.jktu.api.blog.annotation.ResponseMessage;
import cc.jktu.api.blog.dao.entity.User;
import cc.jktu.api.blog.dto.UserRegisterOrLoginRequest;
import cc.jktu.api.blog.exception.WrongPasswordException;
import cc.jktu.api.blog.service.UserService;
import cc.jktu.api.common.CommonResponse;
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
        return new CommonResponse();
    }

    @PostMapping("/login")
    @ResponseMessage("登录成功")
    public CommonResponse login(@RequestBody UserRegisterOrLoginRequest request) {
        final User user = userService.getUserByUsername(request.getUsername());
        if (!userService.checkPassword(request.getPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }
        return new CommonResponse();
    }

}
