package cc.jktu.api.service;

import cc.jktu.api.dao.entity.Post;
import cc.jktu.api.dao.mapper.PostMapper;
import cc.jktu.api.dto.PageResponse;
import cc.jktu.api.exception.PostNotFoundException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    /**
     * 根据id获取文章
     *
     * @param id 文章id
     * @return 文章
     */
    public Post getPostById(Integer id) {
        final Post post = postMapper.selectById(id);
        if (post == null) {
            throw new PostNotFoundException(id.toString());
        }
        return post;
    }

    /**
     * 根据所给页码和分页大小返回多篇文章
     *
     * @param page 页码
     * @param size 分页大小
     * @return 分页结果
     */
    public PageResponse<Post> getPosts(Integer page, Integer size) {
        final LambdaQueryWrapper<Post> queryWrapper = new QueryWrapper<Post>().lambda().orderByDesc(Post::getId);
        final Page<Post> postPage = postMapper.selectPage(new Page<>(page.longValue(), size.longValue()), queryWrapper);
        final PageResponse<Post> pageResponse = new PageResponse<>();
        pageResponse.setTotal(postPage.getTotal());
        pageResponse.setPages(postPage.getPages());
        pageResponse.setSize(postPage.getSize());
        pageResponse.setCurrent(postPage.getCurrent());
        pageResponse.setValues(postPage.getRecords());
        return pageResponse;
    }

    /**
     * 添加文章
     * id会被置为null
     *
     * @param post Post对象
     */
    public void addPost(Post post) {
        post.setId(null);
        postMapper.insert(post);
    }

    /**
     * 根据id更新文章
     *
     * @param post 文章对象，id不可为空
     */
    public void updatePostById(Post post) {
        postMapper.updateById(post);
    }

    /**
     * 根据id删除文章
     *
     * @param id 文章id
     */
    public void removePostById(Integer id) {
        postMapper.deleteById(id);
    }

}
