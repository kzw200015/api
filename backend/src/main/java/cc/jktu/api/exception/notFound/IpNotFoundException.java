package cc.jktu.api.exception.notFound;

import cc.jktu.api.exception.NotFoundException;

public class IpNotFoundException extends NotFoundException {

    private final String ip;

    public IpNotFoundException(String ip) {
        this.ip = ip;
    }

    @Override
    public String getMessage() {
        return String.format("The ip %s is not in the database", this.ip);
    }

}
