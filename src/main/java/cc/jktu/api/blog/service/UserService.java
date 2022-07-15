package cc.jktu.api.blog.service;

import cc.jktu.api.blog.BcryptUtil;
import cc.jktu.api.blog.dao.entity.Post;
import cc.jktu.api.blog.dao.entity.User;
import cc.jktu.api.blog.dao.mapper.PostMapper;
import cc.jktu.api.blog.dao.mapper.UserMapper;
import cc.jktu.api.blog.exception.UserNotFoundException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PostMapper postMapper;

    public void addUser(User user) {
        user.setPassword(BcryptUtil.hashPassword(user.getPassword()));
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
            user.setPassword(BcryptUtil.hashPassword(user.getPassword()));
        }
        userMapper.updateById(user);
    }

    /**
     * 根据id删除用户，一并删除用户所有的文章
     *
     * @param id 用户id
     */
    public void removeUserById(Integer id) {
        postMapper.delete(new QueryWrapper<Post>().lambda().eq(Post::getUserId, id));
        userMapper.deleteById(id);
    }

    public User getUserByUsername(String username) {
        final User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return user;
    }

}
