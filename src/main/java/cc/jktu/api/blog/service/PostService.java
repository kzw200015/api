package cc.jktu.api.blog.service;

import cc.jktu.api.blog.dao.entity.Post;
import cc.jktu.api.blog.dao.mapper.PostMapper;
import cc.jktu.api.blog.dto.PageResponse;
import cc.jktu.api.blog.exception.PostNotFoundException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    public Post getPostById(Integer id) {
        final Post post = postMapper.selectById(id);
        if (post == null) {
            throw new PostNotFoundException(id.toString());
        }
        return post;
    }

    public PageResponse<Post> getPosts(Integer page, Integer size) {
        final Page<Post> postPage = postMapper.selectPage(new Page<>(page.longValue(), size.longValue()), null);
        final PageResponse<Post> pageResponse = new PageResponse<>();
        pageResponse.setTotal(postPage.getTotal());
        pageResponse.setPages(postPage.getPages());
        pageResponse.setSize(postPage.getSize());
        pageResponse.setCurrent(postPage.getCurrent());
        pageResponse.setValues(postPage.getRecords());
        return pageResponse;
    }

    public void addPost(Post post) {
        post.setId(null);
        postMapper.insert(post);
    }

    public void updatePostById(Post post) {
        postMapper.updateById(post);
    }

    public void removePostById(Integer id) {
        postMapper.deleteById(id);
    }

}
