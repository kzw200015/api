package cc.jktu.api.blog.service;

import cc.jktu.api.blog.dao.entity.User;
import cc.jktu.api.blog.dao.mapper.UserMapper;
import cc.jktu.api.blog.exception.UserNotFoundException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public void addUser(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        userMapper.insert(user);
    }

    public User getUserById(Integer id) {
        final User user = userMapper.selectById(id);
        if (user == null) {
            throw new UserNotFoundException(id.toString());
        }
        return user;
    }

    public void updateUserById(User user) {
        if (user.getPassword() != null) {
            user.setPassword(hashPassword(user.getPassword()));
        }
        userMapper.updateById(user);
    }

    public void removeUserById(Integer id) {
        userMapper.deleteById(id);
    }

    public User getUserByUsername(String username) {
        final User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return user;
    }

    public String hashPassword(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }

    public Boolean checkPassword(String plain, String hashed) {
        return BCrypt.checkpw(plain, hashed);
    }

}
