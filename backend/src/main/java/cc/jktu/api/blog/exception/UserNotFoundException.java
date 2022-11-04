package cc.jktu.api.blog.exception;

import cc.jktu.api.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserNotFoundException extends NotFoundException {

    private final String user;

    @Override
    public String getMessage() {
        return String.format("用户 %s 不存在", this.user);
    }

}
