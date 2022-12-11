package cc.jktu.api.dao.mapper;

import cc.jktu.api.dao.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    List<Post> selectPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    Integer count();

    void deleteByUserId(@Param("userId") Integer userId);

    void deleteById(@Param("id") Integer id);

    void insert(@Param("post") Post post);

    void updateById(@Param("post") Post post);

    Post selectById(@Param("id") Integer id);

}
