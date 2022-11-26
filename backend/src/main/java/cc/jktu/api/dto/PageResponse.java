package cc.jktu.api.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class PageResponse<T> {

    private Long total;
    private Long pages;
    private Long size;
    private Long current;
    private Collection<T> values;

}
