package cc.jktu.api.ip.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class IpNotFoundException extends RuntimeException {

    private final String ip;

    public IpNotFoundException(String ip) {
        this.ip = ip;
    }

    @Override
    public String getMessage() {
        return String.format("The ip %s is not in the database", this.ip);
    }

}
