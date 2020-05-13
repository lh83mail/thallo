package io.github.lh83mail.thallo.common.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * {
 * * errcode: 0,
 * * errmsg: "成功",
 * * data: "携带的数据",
 * * total: 0,//分页查询时携带,总记录数
 * * }
 */
@ApiModel(description = "响应数据项(单条记录)")
public class ResponseItem<T> implements Serializable {
    @ApiModelProperty("错误码")
    private Integer code;
    @ApiModelProperty("错误消息")
    private String msg;
    @ApiModelProperty("数据载体")
    private T data;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
