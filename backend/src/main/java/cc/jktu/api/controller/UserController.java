package cc.jktu.api.controller;

import cc.jktu.api.annotation.NeedLogin;
import cc.jktu.api.dao.entity.User;
import cc.jktu.api.dto.UserAddOrUpdateRequest;
import cc.jktu.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @NeedLogin
    public User me(@SessionAttribute("userId") Integer userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("")
    @NeedLogin
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("")
    @NeedLogin
    public void addUser(@RequestBody UserAddOrUpdateRequest request) {
        final User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userService.addUser(user);
    }

    @PatchMapping("/{id}")
    @NeedLogin
    public void updateUserById(@PathVariable("id") Integer id, @RequestBody UserAddOrUpdateRequest request) {
        final User user = new User();
        user.setId(id);
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userService.updateUserById(user);
    }

    @DeleteMapping("/{id}")
    @NeedLogin
    public void removeUserById(@PathVariable("id") Integer id) {
        userService.removeUserById(id);
    }

}
