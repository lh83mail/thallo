package io.github.lh83mail.thallo.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Create At  2020/1/6 18:06
 *
 * @author: Lh
 * @version: 1.0.0
 */
@Data
public class BaseModel<ID> implements Serializable {
    private ID id;
    private String createdBy;
    private Date createAt;
    private String updatedBy;
    private Date updateAt;
}
