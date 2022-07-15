package cc.jktu.api.blog.exception;

public class WrongPasswordException extends RuntimeException {

    @Override
    public String getMessage() {
        return "密码错误";
    }

}
