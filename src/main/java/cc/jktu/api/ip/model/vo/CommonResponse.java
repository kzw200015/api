package cc.jktu.api.ip.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse {

    private Integer status;
    private String message;
    private Object data;

}
