package cc.jktu.api.blog.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public abstract class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

}
