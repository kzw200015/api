package cc.jktu.api.blog.controller;

import cc.jktu.api.blog.dao.entity.Post;
import cc.jktu.api.blog.dto.PageResponse;
import cc.jktu.api.blog.dto.PostAddOrUpdateRequest;
import cc.jktu.api.blog.service.PostService;
import cc.jktu.api.common.dto.CommonResponse;
import cn.dev33.satoken.annotation.SaCheckLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable("id") Integer id) {
        return postService.getPostById(id);
    }

    @GetMapping("")
    public PageResponse<Post> getPosts(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return postService.getPosts(page, size);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @SaCheckLogin
    public CommonResponse addUser(@RequestBody PostAddOrUpdateRequest request) {
        final Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCreateTime(request.getCreateTime());
        post.setUpdateTime(request.getUpdateTime());
        post.setUserId(request.getUserId());
        postService.addPost(post);
        return CommonResponse.noContent();
    }

    @PatchMapping("/{id}")
    @SaCheckLogin
    public CommonResponse updatePostById(@PathVariable("id") Integer id, @RequestBody PostAddOrUpdateRequest request) {
        final Post post = new Post();
        post.setId(id);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCreateTime(request.getCreateTime());
        post.setUpdateTime(request.getUpdateTime());
        post.setUserId(request.getUserId());
        postService.updatePostById(post);
        return CommonResponse.noContent();
    }

    @DeleteMapping("/{id}")
    @SaCheckLogin
    public CommonResponse removePostById(@PathVariable("id") Integer id) {
        postService.removePostById(id);
        return CommonResponse.noContent();
    }

}
