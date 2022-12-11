package cc.jktu.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {

    private Integer total;
    private Integer pages;
    private Integer pageSize;
    private Integer pageNum;
    private List<T> values;

}
