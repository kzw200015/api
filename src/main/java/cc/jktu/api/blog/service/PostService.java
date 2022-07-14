package cc.jktu.api.blog.service;

import cc.jktu.api.blog.dao.entity.Post;
import cc.jktu.api.blog.dao.mapper.PostMapper;
import cc.jktu.api.blog.exception.PostNotFoundException;
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
