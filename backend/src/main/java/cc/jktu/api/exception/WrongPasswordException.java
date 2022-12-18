package cc.jktu.api.exception;

public class WrongPasswordException extends AppException {

    @Override
    public String getMessage() {
        return "密码错误";
    }

}
