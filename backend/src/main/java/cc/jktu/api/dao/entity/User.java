package cc.jktu.api.dao.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

@Data
public class User {

    private Integer id;
    private String username;
    @JSONField(serialize = false)
    private String password;

}
