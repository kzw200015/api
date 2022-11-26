package cc.jktu.api.dto;

import lombok.Data;

@Data
public class UserRegisterOrLoginRequest {

    private String username;
    private String password;

}
