package cc.jktu.api.controller;

import cc.jktu.api.dao.entity.User;
import cc.jktu.api.dto.UserAddOrUpdateRequest;
import cc.jktu.api.service.UserService;
import cc.jktu.api.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public User me() {
        return userService.getUserById(1);
    }

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
        return CommonResponse.noContent();
    }

    @PatchMapping("/{id}")
    public CommonResponse updateUserById(@PathVariable("id") Integer id, @RequestBody UserAddOrUpdateRequest request) {
        final User user = new User();
        user.setId(id);
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userService.updateUserById(user);
        return CommonResponse.noContent();
    }

    @DeleteMapping("/{id}")
    public CommonResponse removeUserById(@PathVariable("id") Integer id) {
        userService.removeUserById(id);
        return CommonResponse.noContent();
    }

}
