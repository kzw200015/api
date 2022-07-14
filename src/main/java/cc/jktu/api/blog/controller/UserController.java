package cc.jktu.api.blog.controller;

import cc.jktu.api.blog.dao.entity.User;
import cc.jktu.api.blog.dto.UserAddOrUpdateRequest;
import cc.jktu.api.blog.service.UserService;
import cc.jktu.api.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse addUser(@RequestBody UserAddOrUpdateRequest request) {
        final User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userService.addUser(user);
        return new CommonResponse();
    }

    @PatchMapping("/{id}")
    public CommonResponse updateUserById(@PathVariable("id") Integer id, @RequestBody UserAddOrUpdateRequest request) {
        final User user = new User();
        user.setId(id);
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userService.updateUserById(user);
        return new CommonResponse();
    }

    @DeleteMapping("/{id}")
    public CommonResponse removeUserById(@PathVariable("id") Integer id) {
        userService.removeUserById(id);
        return new CommonResponse();
    }

}
