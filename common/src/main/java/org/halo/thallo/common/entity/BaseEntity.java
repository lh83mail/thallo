package org.halo.thallo.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.halo.thallo.common.model.BaseModel;
import org.springframework.beans.BeanUtils;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Create At  2020/1/7 11:40
 *
 * @author: Lh
 * @version: 1.0.0
 */
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity<ID, M extends BaseModel<ID>> implements Serializable {
    private ID id;
    private String createdBy;
    private Date createAt;
    private String updatedBy;
    private Date updateAt;

    public void apply(M m) {
        BeanUtils.copyProperties(m, this);
    }

    public M to(Class<M> clazz) {
        M instance = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, instance);
        return instance;
    }
}
