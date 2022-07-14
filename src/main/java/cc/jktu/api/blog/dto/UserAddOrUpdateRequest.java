package cc.jktu.api.blog.dto;

import lombok.Data;

@Data
public class UserAddOrUpdateRequest {

    private String username;
    private String password;

}
