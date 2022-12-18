package cc.jktu.api.dao.mapper;

import cc.jktu.api.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectById(@Param("id") Integer id);

    User selectByUsername(@Param("username") String username);

    List<User> selectList();

    void updateById(@Param("user") User user);

    void deleteById(@Param("id") Integer id);

    Integer count();

    void insert(@Param("user") User user);

}
