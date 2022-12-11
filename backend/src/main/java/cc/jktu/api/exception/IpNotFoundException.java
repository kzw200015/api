package cc.jktu.api.exception;

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
