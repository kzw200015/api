package cc.jktu.api.dao.entity;

import lombok.Data;

@Data
public class Post {

    private Integer id;
    private String title;
    private String content;
    private Long createTime;
    private Long updateTime;
    private Integer userId;

}
