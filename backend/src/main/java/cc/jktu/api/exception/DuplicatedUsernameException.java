package cc.jktu.api.exception;

public class DuplicatedUsernameException extends AppException {

    public DuplicatedUsernameException(String username) {
        super(String.format("用户名 %s 已存在", username));
    }

}
