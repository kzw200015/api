package cc.jktu.api.blog.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@RequiredArgsConstructor
public class UserNotFoundException extends RuntimeException {

    private final String user;

    @Override
    public String getMessage() {
        return String.format("用户 %s 不存在", this.user);
    }

}
