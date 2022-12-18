package cc.jktu.api.exception;

public class UnauthorizedException extends AppException {

    public UnauthorizedException() {
        super("未认证");
    }

}
