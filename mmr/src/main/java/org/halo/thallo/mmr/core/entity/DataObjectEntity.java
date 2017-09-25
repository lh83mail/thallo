package org.halo.thallo.mmr.core.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by dell01 on 2017/9/30.
 */
@Entity
@Table(name="MM_DATA_OBJECTS")
public class DataObjectEntity implements Serializable {

    @Id
    @Column(name="ID_")
    private String id;

    @Column(name="VER_")
    private int version = 0;

    @Column(name="CATEGORY_")
    private String category;

    @Column(name="NAME_")
    private String name;

    @Column(name="DESC_")
    private String description;

    @OneToMany(mappedBy = "owner")
    private Set<AttributeEntity> attributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AttributeEntity> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<AttributeEntity> attributes) {
        this.attributes = attributes;
    }
}
