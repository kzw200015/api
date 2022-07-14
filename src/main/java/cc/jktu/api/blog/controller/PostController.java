package cc.jktu.api.blog.controller;

import cc.jktu.api.blog.dao.entity.Post;
import cc.jktu.api.blog.dto.PostAddOrUpdateRequest;
import cc.jktu.api.blog.service.PostService;
import cc.jktu.api.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable("id") Integer id) {
        return postService.getPostById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse addUser(@RequestBody PostAddOrUpdateRequest request) {
        final Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCreateTime(request.getCreateTime());
        post.setUpdateTime(request.getUpdateTime());
        post.setUserId(request.getUserId());
        postService.addPost(post);
        return new CommonResponse();
    }

    @PatchMapping("/{id}")
    public CommonResponse updatePostById(@PathVariable("id") Integer id, @RequestBody PostAddOrUpdateRequest request) {
        final Post post = new Post();
        post.setId(id);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCreateTime(request.getCreateTime());
        post.setUpdateTime(request.getUpdateTime());
        post.setUserId(request.getUserId());
        postService.updatePostById(post);
        return new CommonResponse();
    }

    @DeleteMapping("/{id}")
    public CommonResponse removePostById(@PathVariable("id") Integer id) {
        postService.removePostById(id);
        return new CommonResponse();
    }

}
