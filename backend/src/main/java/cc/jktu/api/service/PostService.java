package cc.jktu.api.service;

import cc.jktu.api.dao.entity.Post;
import cc.jktu.api.dao.mapper.PostMapper;
import cc.jktu.api.dto.Page;
import cc.jktu.api.exception.notFound.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

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
     * @param pageSize 页码
     * @param pageNum  分页大小
     * @return 分页结果
     */
    public Page<Post> getPosts(Integer pageNum, Integer pageSize) {
        final List<Post> posts = postMapper.selectPage(pageNum, pageSize);
        final Integer total = postMapper.count();
        final Page<Post> page = new Page<>();
        page.setTotal(total);
        page.setPages((int) Math.ceil(((double) total) / pageSize));
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setValues(posts);
        return page;
    }

    /**
     * 添加文章
     * id会被置为null
     *
     * @param post Post对象
     */
    public void addPost(Post post) {
        post.setId(null);
        post.setCreateTime(Instant.now().getEpochSecond());
        post.setUpdateTime(Instant.now().getEpochSecond());
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
