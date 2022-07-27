package cc.jktu.api.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse {

    private Integer status;
    private String message;
    private Object data;

    public static CommonResponse noContent(HttpStatus status, String message) {
        final CommonResponse response = new CommonResponse();
        response.setStatus(status.value());
        response.setMessage(message);
        return response;
    }

    public static CommonResponse noContent(HttpStatus status) {
        return noContent(status, status.name());
    }

    public static CommonResponse noContent() {
        return new CommonResponse();
    }

}
