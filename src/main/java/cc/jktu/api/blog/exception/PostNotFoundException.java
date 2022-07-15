package cc.jktu.api.blog.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostNotFoundException extends NotFoundException {

    private final String post;

    @Override
    public String getMessage() {
        return String.format("文章 %s 不存在", post);
    }

}
