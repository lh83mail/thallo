package org.halo.thallo.mmr.core.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dell01 on 2017/9/30.
 */
@Entity
@Table(name="MM_ATTRIBUTES")
public class AttributeEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name="ID_")
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="OWNER_")
    private DataObjectEntity owner;

    @Column(name="CODE_")
    private String code;    // 属性名称

    @Column(name="NAME_")
    private String name;

    @Column(name="DTYPE_")
    private String dataType;    // 数据类型: 集合, K-V , 单值, 引用

    @Column(name="VTYPE_")
    private String valueType;   // 值类型: 整数, 浮点, 字串, 枚举, 日期, 时间

    @Column(name="VALUE_")
    private String value;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DataObjectEntity getOwner() {
        return owner;
    }

    public void setOwner(DataObjectEntity owner) {
        this.owner = owner;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
