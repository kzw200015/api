package cc.jktu.api.controller;

import cc.jktu.api.annotation.NeedLogin;
import cc.jktu.api.dao.entity.Post;
import cc.jktu.api.dto.Page;
import cc.jktu.api.dto.PostAddOrUpdateRequest;
import cc.jktu.api.service.PostService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("")
    public Page<Post> getPosts(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return postService.getPosts(page, size);
    }

    @PostMapping("")
    @NeedLogin
    public void addPost(@RequestBody PostAddOrUpdateRequest request) {
        final Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCreateTime(request.getCreateTime());
        post.setUpdateTime(request.getUpdateTime());
        post.setUserId(request.getUserId());
        postService.addPost(post);
    }

    @PatchMapping("/{id}")
    @NeedLogin
    public void updatePostById(@PathVariable("id") Integer id, @RequestBody PostAddOrUpdateRequest request) {
        final Post post = new Post();
        post.setId(id);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCreateTime(request.getCreateTime());
        post.setUpdateTime(request.getUpdateTime());
        post.setUserId(request.getUserId());
        postService.updatePostById(post);
    }

    @DeleteMapping("/{id}")
    @NeedLogin
    public void removePostById(@PathVariable("id") Integer id) {
        postService.removePostById(id);
    }

}
