package cc.jktu.api.service;

import cc.jktu.api.dao.entity.User;
import cc.jktu.api.dao.mapper.PostMapper;
import cc.jktu.api.dao.mapper.UserMapper;
import cc.jktu.api.exception.DuplicatedUsernameException;
import cc.jktu.api.exception.UserNotFoundException;
import cc.jktu.api.util.BcryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PostMapper postMapper;

    /**
     * 添加用户
     * id会被置为null
     * password会被哈希处理后存入
     *
     * @param user 用户对象
     */
    public void addUser(User user) {
        user.setId(null);
        user.setPassword(BcryptUtil.hashPassword(user.getPassword()));
        try {
            userMapper.insert(user);
        } catch (DuplicateKeyException e) {
            throw new DuplicatedUsernameException(user.getUsername());
        }
    }

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return 用户
     */
    public User getUserById(Integer id) {
        final User user = userMapper.selectById(id);
        if (user == null) {
            throw new UserNotFoundException(id.toString());
        }
        return user;
    }

    /**
     * 根据id更新用户
     * password会被哈希处理后存入
     *
     * @param user 用户对象，id不可为空
     */
    public void updateUserById(User user) {
        if (user.getPassword() != null) {
            user.setPassword(BcryptUtil.hashPassword(user.getPassword()));
        }
        userMapper.updateById(user);
    }

    /**
     * 根据id删除用户，一并删除用户所有的文章
     *
     * @param userId 用户id
     */
    public void removeUserById(Integer userId) {
        postMapper.deleteByUserId(userId);
        userMapper.deleteById(userId);
    }

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    public User getUserByUsername(String username) {
        final User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return user;
    }

    public List<User> getUsers() {
        return userMapper.selectList();
    }

}
