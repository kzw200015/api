package cc.jktu.api.blog.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@RequiredArgsConstructor
public class PostNotFoundException extends RuntimeException {

    private final String post;

    @Override
    public String getMessage() {
        return String.format("文章 %s 不存在", post);
    }

}
