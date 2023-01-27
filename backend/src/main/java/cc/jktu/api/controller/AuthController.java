package cc.jktu.api.controller;

import cc.jktu.api.annotation.NeedAuth;
import cc.jktu.api.dao.entity.User;
import cc.jktu.api.dto.UserRegisterOrLoginRequest;
import cc.jktu.api.exception.WrongPasswordException;
import cc.jktu.api.service.UserService;
import cc.jktu.api.util.BcryptUtil;
import jakarta.servlet.http.HttpSession;
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
    public void register(@RequestBody UserRegisterOrLoginRequest request) {
        final User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userService.addUser(user);
    }

    @PostMapping("/login")
    public void login(@RequestBody UserRegisterOrLoginRequest request, HttpSession session) {
        final User user = userService.getUserByUsername(request.getUsername());
        if (!BcryptUtil.checkPassword(request.getPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }

        session.setAttribute("userId", user.getId());
    }

    @PostMapping("/logout")
    @NeedAuth
    public void logout(HttpSession session) {
        session.invalidate();
    }

}
