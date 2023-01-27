package cc.jktu.api.controller;

import cc.jktu.api.annotation.NeedAuth;
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
    public Page<Post> getPosts(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return postService.getPosts(pageNum, pageSize);
    }

    @PostMapping("")
    @NeedAuth
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
    @NeedAuth
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
    @NeedAuth
    public void removePostById(@PathVariable("id") Integer id) {
        postService.removePostById(id);
    }

}
