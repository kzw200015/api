package cc.jktu.api.dto;

import lombok.Data;

@Data
public class PostAddOrUpdateRequest {

    private String title;
    private String content;
    private Long createTime;
    private Long updateTime;
    private Integer userId;

}
