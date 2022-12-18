package cc.jktu.api.exception.notFound;

import cc.jktu.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostNotFoundException extends NotFoundException {

    private final String post;

    @Override
    public String getMessage() {
        return String.format("文章 %s 不存在", post);
    }

}
