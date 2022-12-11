package cc.jktu.api.dto;

import lombok.Data;

@Data
public class CommonResponse<T> {

    private String msg = "success";
    private T data;

    public static CommonResponse<Void> noContent(String msg) {
        final CommonResponse<Void> response = new CommonResponse<>();
        response.setMsg(msg);
        return response;
    }

}
