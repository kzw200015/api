package cc.jktu.api.blog.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("posts")
public class Post extends BaseEntity {

    private String title;
    private String content;
    private Long createTime;
    private Long updateTime;
    private Integer userId;

}
