package org.halo.thallo.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.halo.thallo.common.model.BaseModel;

import javax.persistence.*;

/**
 * Create At  2020/1/7 11:43
 *
 * @author: Lh
 * @version: 1.0.0
 */
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
public class UuidBaseEntity<M extends BaseModel<String>> extends BaseEntity<M> {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 50)
    protected String id;

}
