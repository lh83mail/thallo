package io.github.lh83mail.thallo.common.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

@ApiModel(description = "响应数据项(集合)")
public class ResponseList<T> extends ResponseItem<Collection<T>> {
    @ApiModelProperty("记录数")
    private Long total;


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}