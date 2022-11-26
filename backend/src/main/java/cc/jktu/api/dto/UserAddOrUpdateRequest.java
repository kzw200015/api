package cc.jktu.api.dto;

import lombok.Data;

@Data
public class UserAddOrUpdateRequest {

    private String username;
    private String password;

}
