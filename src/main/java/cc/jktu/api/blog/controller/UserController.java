package cc.jktu.api.blog.controller;

import cc.jktu.api.blog.dao.entity.User;
import cc.jktu.api.blog.dto.UserAddOrUpdateRequest;
import cc.jktu.api.blog.service.UserService;
import cc.jktu.api.common.CommonResponse;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public User me() {
        return userService.getUserById(StpUtil.getLoginIdAsInt());
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @SaCheckLogin
    public CommonResponse addUser(@RequestBody UserAddOrUpdateRequest request) {
        final User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userService.addUser(user);
        return CommonResponse.noContent();
    }

    @PatchMapping("/{id}")
    @SaCheckLogin
    public CommonResponse updateUserById(@PathVariable("id") Integer id, @RequestBody UserAddOrUpdateRequest request) {
        final User user = new User();
        user.setId(id);
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userService.updateUserById(user);
        return CommonResponse.noContent();
    }

    @DeleteMapping("/{id}")
    @SaCheckLogin
    public CommonResponse removeUserById(@PathVariable("id") Integer id) {
        userService.removeUserById(id);
        return CommonResponse.noContent();
    }

}
