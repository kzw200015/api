package cc.jktu.api.controller;

import cc.jktu.api.dao.entity.User;
import cc.jktu.api.dto.UserLoginResponse;
import cc.jktu.api.dto.UserRegisterOrLoginRequest;
import cc.jktu.api.service.UserService;
import cc.jktu.api.annotation.ResponseMessage;
import cc.jktu.api.dto.CommonResponse;
import cc.jktu.api.exception.WrongPasswordException;
import cc.jktu.api.util.BcryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
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
        final UserLoginResponse response = new UserLoginResponse();
        return response;
    }

    @PostMapping("/logout")
    @ResponseMessage("注销成功")
    public CommonResponse logout() {
        return CommonResponse.noContent();
    }

}
